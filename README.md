cdi-resteasy-hibernate-angularjs-wildfly-postgresql-sample
==========================================================

Sample application with full CRUD for Airport codes to showcase following technologies: 
- Java 8
- CDI
- RestEasy (JAX-RS) 
- JPA / Hibernate
- AngularJS 
- Bootstrap 
- Maven (build) 

Setup WildFly 9.0 standalone.xml
--------------------------------
```xml
<subsystem xmlns="urn:jboss:domain:datasources:3.0">
	<datasources>
		<datasource jta="true" jndi-name="java:/jdbc/test" pool-name="test" enabled="true" use-java-context="true">
			<connection-url>jdbc:postgresql://localhost:5432/test</connection-url>
			<driver-class>org.postgresql.Driver</driver-class>
		    <driver>postgresql</driver>
		    <pool>
		        <min-pool-size>2</min-pool-size>
		        <max-pool-size>20</max-pool-size>
		    </pool>
		    <security>
		        <user-name>test</user-name>
		    </security>
		</datasource>
	    <drivers>
	        <driver name="postgresql" module="org.postgresql">
	            <xa-datasource-class>org.postgresql.xa.PGXADataSource</xa-datasource-class>
	        </driver>
	    </drivers>
	</datasources>
</subsystem>
```

Setup sample.war
----------------
- Use your favorite IDE to deploy and run the application. 
- Use maven command to deploy to AS. NOTE: maven settings.xml need to be configured accordingly. 
```bash
$ mvn clean deploy
```

Access
------
- via browser using url: 
```
http://localhost:8080/sample 
```
![Alt text](/sample.png?raw=true "Sample page")

- via REST client using url: 
```html
GET http://localhost:8080/sample/api/airpots
GET http://localhost:8080/sample/api/airpots/123 (using id)
GET http://localhost:8080/sample/api/airpots/NYC (using code)
GET http://localhost:8080/sample/api/airpots/search/Wash (using text) 
POST http://localhost:8080/sample/api/airpots
PUT http://localhost:8080/sample/api/airpots
DELETE http://localhost:8080/sample/api/airpots/123 (using id)
```

