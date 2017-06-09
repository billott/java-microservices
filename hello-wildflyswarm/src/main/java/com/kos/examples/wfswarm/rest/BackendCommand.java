package com.kos.examples.wfswarm.rest;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

/**
 * Created by billott 
 */
public class BackendCommand extends HystrixCommand<BackendDTO> {

    private String host;
    private int port;
    private String saying;

    public BackendCommand(String host, int port) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("wildflyswarm.backend"))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                                .withCoreSize(10)
                                .withMaxQueueSize(-1))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                                .withCircuitBreakerEnabled(true)
                                .withCircuitBreakerRequestVolumeThreshold(5)
                                .withMetricsRollingStatisticalWindowInMilliseconds(5000)
                ))
        ;
        this.host = host;
        this.port = port;
    }

    public BackendCommand withSaying(String saying) {
        this.saying = saying;
        return this;
    }

    @Override
    protected BackendDTO run() throws Exception {
        String backendServiceUrl = String.format("http://%s:%d",  host, port);
        System.out.println("Sending to: " + backendServiceUrl);

        Client client = ClientBuilder.newClient();
        return client.target(backendServiceUrl)
                .path("api")
                .path("backend")
                .queryParam("greeting", saying)
                .request(MediaType.APPLICATION_JSON_TYPE).get(BackendDTO.class);

    }

    @Override
    protected BackendDTO getFallback() {
        BackendDTO rc = new BackendDTO();
        rc.setGreeting("Greeting fallback!");
        rc.setIp("127.0.0,1");
        rc.setTime(System.currentTimeMillis());
        return rc;
    }


}
