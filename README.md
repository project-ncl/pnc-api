# pnc-api

## Description

pnc-api is used to place the REST interfaces and DTOs used by the various
Project Newcastle services.

Storing the interfaces and DTOs in a common shared library allows for easier
generation of client and server code.

This is still a work in progress as we continue to migrate all the interfaces
and DTOs here.

## Building
```bash
$ mvn clean install
```

## Consuming pnc-api in your project
You can simply use the pnc-api library by specifying the dependency in your
Maven project's pom.xml:
```xml
<dependency>
  <groupId>org.jboss.pnc</groupId>
  <artifactId>pnc-api</artifactId>
  <version>LATEST_VERSION</version>
</dependency>
```

Using the Eclipse Transformer Maven plugin, we also produce a jar with Jakarta
annotations. You can consume it in your project as:
```
<dependency>
  <groupId>org.jboss.pnc</groupId>
  <artifactId>pnc-api</artifactId>
  <version>LATEST_VERSION</version>
  <classifier>jakarta</classifier>
</dependency>
```

Once we migrate all of our annotations / classes from `javax` to `jakarta`, the
classifier won't be needed anymore.

The latest version is specified [here](https://repo1.maven.org/maven2/org/jboss/pnc/pnc-api/maven-metadata.xml)
Snapshot versions are published [here](https://repository.jboss.org/org/jboss/pnc/pnc-api/)
