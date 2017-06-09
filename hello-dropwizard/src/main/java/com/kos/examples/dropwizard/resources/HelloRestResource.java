
package com.kos.examples.dropwizard.resources;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by billott 
 */
@Path("/api")
public class HelloRestResource {

    private String saying;
    public HelloRestResource(final String saying) {
        this.saying = saying;
    }

    @Path("/hello")
    @GET
    @Timed
    public String hello() throws UnknownHostException {
        String hostname = null;
        try {
            hostname = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            hostname = "unknown";
        }
        return saying + " " + hostname;
    }
}
