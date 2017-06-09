
# Java Microservice Architecture Collection

A collection of Microservice Architectures implemented with Spring Framework, Dropwizard, and JBoss Wildfly Swarm. This repo contains the one sample application in 4 different implementations:

1. Dropwizard - A classic microservice implementation uses Jetty as servlet, Jersey for REST service, Jackson for JSON marshalling, Hibernate, Gauva, Metrics, Logback (JFJ4), and JDBI.

2. Spring Boot - A Spring Framework microservice implementation provides Simplified Configuration, Starter Dependencies, Automates Application Packaging, Exposes production ready metrics, etc. 

3. Wildfly - JBoss microservice implementation using API fractions of the Java EE applicatoin server.

4. Backend - A classic servlet used as a micoservice back end resource


## Maven Artifacts used by Java Microservice Collection

1. Dropwizard simplefies RESTFul web service development and offers sophisticated configuration, application metrics, logging, operational tools, and much more.  You are able produce and ship production-quality HTTP+JSON web service.

2. OpenShift - Redhat Cloud container is a supported distribution using the Kubernetes tool for Docker container packaging. OpenShift can do compile source, deploy application, do rolling updates to executables, multi-tenacy user isolation, and various infrastructure services.

3. Kubernetes is an open-source container as service for automating deployment, orchestration, scaling, discovery, and management of containerized applications. Supports other container implementations like Docker. Quick description - Pods are collection of one or more Docker containers, Replication Controller (template driven) creates and manages the Pods, Services are IP proxy for a collection of Pods, and Labels (key/value pairs) are grouping and organization of objects in JSON/YAML template.

4. Docker - an open source project that automates the deployment of applications inside Linux containers.

5. Fabric8 is a integration tool from JBOSS Fuse project as a intergration adapter to be used in the Redhat OpenShift. It improves container cohesion for microservice platform based on Docker and Kubernetes. DevOps use Jenikins with Fabric8 to provide a flexible and automated setup for a Continous Integration and Delivery pipeline on a per project basis. Fabric8 is considered integration-Platform-as-a-Service (iPaaS) which is centered around Camel and ActiveMQ providing rich visualisations and one click installations. Fabric8 has a web console to manage Kubernetes - Services, minion, Pods and helps with complex dependencies during setup.

6. Jetty is Java HTTPD (Web) Server with Java servlet container (API) supports HTTP2 and WebSocket protocols used in building web services in microservice architectures.

7. Apache Maven is build automation tool for Java. It describes how to build software, and describes the build dependencies, like JARs, required by the defined build artifacts.

8. Hystrix developed by Netflix and used as a latency and fault tolerance library designed to isolate points of access to remote systems that are multi-threaded environments, services and libraries. Also does  block/stop cascading failures (Exceptions) and enable relience in complex distribution systems.

9. Ribbon is Netflix Inter Process communication library (RPC) with built in software load balancers. Priminary usage involves REST calls with various serialization scheme support.

10. Wildfly-Swarm a package manager to reduce class inventory and JAR size, adds service discovery and service registries. 

11. Deltaspike is a collection of portable Java Contexts and Dependency Injection (CDI) extensions for managing dependency injection and contextual lifecycles.

12. Jolokia is remote JMX bridge using JSON over HTTP for monitoring performance.
