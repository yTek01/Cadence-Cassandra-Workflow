package com.cadence;

import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowClientOptions;
import com.uber.cadence.serviceclient.ClientOptions;
import com.uber.cadence.serviceclient.WorkflowServiceTChannel;
import com.uber.cadence.worker.WorkerFactory;

import com.uber.cadence.worker.Worker;
import com.uber.cadence.workflow.Workflow;
import com.uber.cadence.workflow.WorkflowMethod;
import org.slf4j.Logger;


public class App {
    public static void main(String[] args) {
        WorkflowClient workflowClient = 
            WorkflowClient.newInstance(
                new WorkflowServiceTChannel(ClientOptions.defaultInstance()),
                WorkflowClientOptions.newBuilder().setDomain("hello-domain").build()
            );

        WorkerFactory factory = WorkerFactory.newInstance(workflowClient);
        Worker worker = factory.newWorker("helloTASK");
        worker.registerWorkflowImplementationTypes(HelloWorldImpl.class);
        factory.start();

        HelloWorld helloworld = new HelloWorldImpl();
        helloworld.sayHello("Cadence");
    }
    

    private static Logger logger = Workflow.getLogger(App.class);
    public interface HelloWorld {
        @WorkflowMethod       
        String sayHello(String name);
    }

    public static class HelloWorldImpl implements HelloWorld {
        @Override
        public String sayHello(String name) {
            logger.info("Data passed to UI");
            return "Welcome to Cadence Workflow, " + name + "!";
        }
    } 
}