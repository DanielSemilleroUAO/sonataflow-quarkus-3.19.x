package com.daniel;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.kie.kogito.Model;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;

import java.util.HashMap;
import java.util.Map;

@Path("hello")
public class GreetingResource {

    private static final String WORKFLOW_DATA = "workflowdata";

    @Inject
    @Named("hello_world")
    Process<? extends Model> hello_world;


    @POST
    public Response executeWorkflowHello(Map<String, Object> input) {
        return initAndExecuteWorkflow(hello_world, input);
    }

    public static Response initAndExecuteWorkflow(Process<? extends Model> inputModel, Map<String, Object> input) {
        Model model = inputModel.createModel();
        model.fromMap(input);
        ProcessInstance<? extends Model> instance = inputModel.createInstance(model);
        instance.start();
        return mapResponse(instance.variables().toMap().get(WORKFLOW_DATA));
    }

    public static Response mapResponse(Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("Data", data);
        return Response.ok(map).build();
    }
}
