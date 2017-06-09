package com.example;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import org.springframework.web.client.RestTemplate;

/**
 * Created by billott 
 */
public class BackendCommand extends HystrixCommand<BackendDTO> {

    private String host;
    private int port;
    private String saying;
    private RestTemplate template;

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

    public BackendCommand withTemplate(RestTemplate template) {
        this.template = template;
        return this;
    }


    @Override
    protected BackendDTO run() throws Exception {
        String backendServiceUrl = String.format("http://%s:%d/api/backend?greeting={greeting}",  host,port);
        System.out.println("Sending to: " + backendServiceUrl);
        return template.getForObject(backendServiceUrl, BackendDTO.class, saying);
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
