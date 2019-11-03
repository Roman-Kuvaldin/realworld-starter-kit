package io.realword.backend.vertx.app;

import io.realword.backend.vertx.config.AppConfig;
import io.vertx.config.ConfigRetriever;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;

public class MainVerticle extends AbstractVerticle {

    private HttpServer httpServer;
    private static final Logger LOGGER = LoggerFactory.getLogger("MAIN");

    public Promise<JsonObject> readConfig() {
        LOGGER.info("readConfig");
        Promise<JsonObject> result = Promise.promise();
        ConfigRetriever configRetriever = ConfigRetriever.create(vertx);
        configRetriever.getConfig(hndlr -> {
            if (hndlr.failed()) {
                // Return default config
                LOGGER.error("Handler failed: ", hndlr.cause());
                result.complete(new JsonObject());
                return;
            }
            JsonObject config = hndlr.result();
            LOGGER.info("Handler OK:", config);
            LOGGER.info(config.encodePrettily());
            result.complete(config);
        });
        return result;
    } // END readConfig

    private void startServer(JsonObject config) {
        String portValue = AppConfig.port(config);
        int port = Double.valueOf(portValue).intValue();

        LOGGER.info("Starting server on port {0}", port);
        Router router = Router.router(vertx);
        router.route().last().handler((rc) -> {
            rc.response()
                    .putHeader(HttpHeaders.CONTENT_TYPE, "text/plain")
                    .end("Hello");
        });
        httpServer = vertx.createHttpServer();
        httpServer.requestHandler(router);
        httpServer.listen(port);
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        LOGGER.info("MainVerticle.start");
        Promise<JsonObject> readConfig = readConfig();
        readConfig.future().setHandler(hndlr -> {
            if (hndlr.failed()) {
                startPromise.fail(hndlr.cause());
                return;
            }
            startServer(hndlr.result());
            LOGGER.info("Server started");
        });
    }

    @Override
    public void stop(Promise<Void> stopPromise) throws Exception {
        LOGGER.info("MainVerticle.stop");
        httpServer.close(stopPromise);
        super.stop(stopPromise);
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MainVerticle());
    }
} // END MainVerticle
