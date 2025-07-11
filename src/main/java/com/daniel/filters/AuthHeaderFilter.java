package com.daniel.filters;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.Response;
import java.io.IOException;

@Provider
public class AuthHeaderFilter implements ContainerRequestFilter {

    private static final String AUTH_HEADER = "X-Auth";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (requestContext.getHeaderString(AUTH_HEADER) == null) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Missing X-Auth header").build());
        }
    }
}
