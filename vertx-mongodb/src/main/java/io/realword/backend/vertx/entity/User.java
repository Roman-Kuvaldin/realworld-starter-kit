package io.realword.backend.vertx.entity;

/**
 * Represents user in system <br>
 *
 * Users (for authentication) <br>
 * { "user": { "email": "jake@jake.jake", "token": "jwt.token.here", "username":
 * "jake", "bio": "I work at statefarm", "image": null } }
 *
 * @author roman.kuvaldin@gmail.com
 */
public interface User {

    String getEmail();

    String getUsername();

    String getIcon();

    String getBio();

} // END User
