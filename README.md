# Europeana CoreLib

The CoreLib repository contains the libraries that provide the underlying functionality (i.e. search and ingestion) for both the [Portal](https://github.com/europeana/portal/) and [API](https://github.com/europeana/api2/).

## Build requirements
### Runtime
* Java version "1.7.0_55", OpenJDK Runtime Environment (IcedTea 2.4.7)

or

* Java version "1.7.0_67", Oracle Java(TM) SE Runtime Environment (build 1.7.0_67-b01)

### Tools
* Maven (v2.2.1), although v3.x may also work

## Build
``mvn clean install`` (add ``-DskipTests``) to skip the unit tests during build

### Known issues
Note: there are a number of older/outdated libraries still being referenced as dependencies, some of which may not be provided anymore by the central repositories. The Europeana artifactory has a copy of these dependencies, so to adding this repository successfully build the code, 

* Maven 2.2.1: The 2.2.1 version of Maven has a [known issue](http://jira.codehaus.org/browse/WAGON-314) that it does not follow redirects (HTTP 301). This manifests itself currently in trying to download the net.java.jvnet-parent:1 pom. A workaround is to add the following mirror settings to ``<mirrors>`` section of the maven settings file (usually found in ~/.m2/settings):
```bash
</mirror>
    <mirror>
    <id>europeana-ext-release-local</id>
    <url>http://artifactory.eanadev.org/artifactory/ext-release-local</url>
    <mirrorOf></mirrorOf>
</mirror>
``` 
