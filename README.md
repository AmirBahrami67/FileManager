This is a Spring project providing RESTful API for managing files on AWS S3.
Features:
* Spring Boot is configured to support RESTFul APIs.
* AWS libraries are configured and FileMagaer services implemented using AWS S3 client.
* Swagger framework is configured and ready to use to help developers design, build, document, and consume RESTFul APIs
* Spring Actuator is configured to manage application at production.
* Lombok library is configured to help avoiding boiler-plate codes in Java.
* Spock testing framework is configured to support unit tests.
* checkstyle plugin is configured with Google code style standards for static code analysis.
* PMD plugin is configured with a reasonable rules configuration for static code analysis.

# Getting Started

### IDE SETUP
#### IntelliJ IDEA configuration
##### plugins to install and enable
* Lombok ==> enable annotation processing for Lombok
* google-java-format  ==> when it's enabled just reformat source files to follow the standards. <code>Code -> Reformat Code</code>

##### create Java debug run configuration
<code>Run -> Edit Configuration -> add "Remote" configuration configured on port 5005</code>

### Build and run Project using Gradle command line
* build ==> <code>gradlew build</code>
* publish docker image ==> <code>gradlew bootBuildImage</code>
* run ==> <code>gradlew bootRun</code>
* run debug mode ==> <code>gradlew --debug-jvm</code>
* run tests ==> <code>gradlew test</code>
* run a specific test ==> <code>gradlew test --tests \*TEST_NAME\*</code>

### Default project urls
* [Home](http://localhost:8080)
* [Actuator](http://localhost:8080/actuator)
* [Actuator build info](http://localhost:8080/actuator/info)
* [Swagger UI](http://localhost:8080/swagger-ui.html)

# Technologies
### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.0.M4/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.0.M4/gradle-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#using-boot-devtools)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Validation](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-validation)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#production-ready)
* [Resilience4J](https://cloud.spring.io/spring-cloud-static/spring-cloud-circuitbreaker/current/reference/html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

