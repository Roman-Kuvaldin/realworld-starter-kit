package io.realword.backend.vertx.app;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

/**
 * Register all endpoints
 *
 * @author roman.kuvaldin@gmail.com
 */
public class RouterBuilder {

    public static enum Route {
        LOGIN("api/users/login", "users-login", false),
        PAGE404("", "");

        private final String route;
        private final String queue;
        private final boolean requiresAuth;

        private Route(String route, String queue) {
            this.route = route;
            this.queue = queue;
            this.requiresAuth = true;
        }

        private Route(String route, String queue, boolean requiresAuth) {
            this.route = route;
            this.queue = queue;
            this.requiresAuth = requiresAuth;
        }
    }

    public static Handler<RoutingContext> createHandler(Route route) {
        return null;
    }

    public static Router build(Vertx vertx) {
        Router router = Router.router(vertx);
        router.route(HttpMethod.POST, Route.LOGIN.route).handler(createHandler(Route.LOGIN));
        router.route().last().handler((rc) -> {
            // Add all routes
            // Last route is 404
            rc.response().setStatusCode(404).setStatusMessage("Not found").end();
        });
        return router;
    }

} // END RouterBuilder
