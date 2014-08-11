cf-services101
==============

Cloudfoundry convienient manages service dependencies for the apps. This repo
take the example of injecting a datasource to explain different ways wiring services
in cloudfoundry.

## Running the app in local

<pre><code>
mvn clean package && java -jar target/demo-0.0.1-SNAPSHOT.jar
</code></pre>

The app will run locally with H2 in memory database configured as [H2DataSourceConfig]()

http://localhost:8080/test would show some dummy string in database

## Running the app in cloudfoundry

There are four ways of injecting dependencies, in this example:

* [EnvironmentDataConfig](src/main/java/demo/data/EnvironmentDataConfig.java): Using environment variables with spring profile, parse-env
* [ParseServiceDataConfig](src/main/java/demo/data/ParseServiceDataConfig.java): Bind with cloudfoundry broked service and parse the VCAP_ENV variables with spring profile, parse-vcap
* [ParseUserDefinedDataConfig](src/main/java/demo/data/ParseUserDefinedDataConfig.java): Bind with cloudfoundry user defined service and parse the VCAP_ENV variables with spring profile, user-defined
* [SpringCloudDataConfig](src/main/java/demo/data/SpringCloudDataConfig.java): Bind with cloudfoundry with spring cloud framework. spring profile: spring-cloud

## How to deploy these examples using the application

### Deploy the in-memory database dependency to the app
* The manifest file is using default
<pre><code>cf push</code></pre>

### Bind the service to the application

* Create a service with pivotal cf mysql services broker
<pre><code>cf help create-service p-mysql plan test</code></pre>

* Bind the service to the demo app
<pre><code>cf bind-service demo test</code></pre>

### Create tables and schemas to the database

*Find the database credentials for the demo app
<pre><code>cf files demo logs/env.log</code></pre>

The database information will be found in the VCAP_SERVICES env variables
User should connect to the database with any mysql client and run the [test.sql](src/main/resources/test.sql) to create data

### Deploy the app with different profile

By changing the manifest.yml SPRING_PROFILES_ACTIVE value, user is able to test and understand different strategies of the service dependencies.
