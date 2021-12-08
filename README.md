# Spring Native 0.11.x 


## Major Themes for this Video 
* the new AOT engine 
* the free performance bump you get, even on the JRE, shown with `rss.sh`
* the new interfaces, the new service loaders, and the fact that these get run at the build time even though they can now live in the same jar as your autoconfig and everything else and be brought in to the build in the same way as the autoconfiguration.
* the road to Spring Framework 6 and Spring Boot 3 

## Outline 

* getting the bits: Spring Native 0.11.snapshots 
* a reactive, non-blocking http app talking to an embedded SQL DB
* introducing the new AOT engine. You can preflight what its doing with `spring-aot:generate`
* free performance bump even on the JRE:  `-DspringAot=true`
* the results are astonishing! 
 * look at reactive apps (rss, startup: jre, native)
 * look at batch(rss, startup: jre, native)
 * look at traditional apps with JPA/Tomcat/Actuator(rss, startup: jre, native)
 
* extensibility
 * lives alongside autoconfig
 * hints
 * `NativeConfiguration` 
 * `BeanFactoryNativeConfigurationProcessor` 
 * `BeanNativeConfigurationProcessor` 


