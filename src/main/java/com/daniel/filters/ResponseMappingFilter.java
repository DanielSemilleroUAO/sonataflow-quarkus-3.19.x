package com.daniel.filters;

import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Map;

import org.kie.kogito.serverless.workflow.models.JsonNodeModelOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ResponseMappingFilter implements ContainerResponseFilter {

    private static final Logger logger = LoggerFactory.getLogger(ResponseMappingFilter.class);

    @Override
    public void filter(jakarta.ws.rs.container.ContainerRequestContext requestContext,
            ContainerResponseContext responseContext) throws IOException {
        JsonNodeModelOutput entity = (JsonNodeModelOutput) responseContext.getEntity();
        logger.info("Response: {}", entity.getWorkflowdata());
        if (entity instanceof JsonNodeModelOutput) {

                responseContext.setEntity(entity.getWorkflowdata());
                responseContext.getHeaders().putSingle("Content-Type", MediaType.APPLICATION_JSON);
            
        }
    }
}
