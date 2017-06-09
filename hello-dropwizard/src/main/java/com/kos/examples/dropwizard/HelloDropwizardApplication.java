package com.kos.examples.dropwizard;

import com.kos.examples.dropwizard.resources.GreeterRestResource;
import com.kos.examples.dropwizard.resources.GreeterSayingFactory;
import com.kos.examples.dropwizard.resources.HelloRestResource;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.ws.rs.client.Client;

public class HelloDropwizardApplication extends Application<HelloDropwizardConfiguration> {

    public static void main(final String[] args) throws Exception {
        new HelloDropwizardApplication().run(args);
    }

    @Override
    public String getName() {
        return "HelloDropwizard";
    }

    @Override
    public void initialize(final Bootstrap<HelloDropwizardConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );
    }

    @Override
    public void run(final HelloDropwizardConfiguration configuration,
                    final Environment environment) {

        // simple Hello service
        environment.jersey().register(new HelloRestResource(configuration.getSayingFactory().getSaying()));


        // greeter service
        GreeterSayingFactory greeterSayingFactory = configuration.getGreeterSayingFactory();
        Client greeterClient = new JerseyClientBuilder(environment)
                .using(greeterSayingFactory.getJerseyClientConfig())
                .build("greeterClient");

        environment.jersey().register(new GreeterRestResource(greeterSayingFactory.getSaying(),
                greeterSayingFactory.getHost(),
                greeterSayingFactory.getPort(), greeterClient));
    }

}
