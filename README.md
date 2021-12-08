# Spring Native 0.11.x 


## Major Themes for this Video 
* the new AOT engine 
* the free performance bump you get, even on the JRE, shown with `rss.sh`
* the new interfaces, the new service loaders, and the fact that these get run at the build time even though they can now live in the same jar as your autoconfig and everything else and be brought in to the build in the same way as the autoconfiguration.
* the road to Spring Framework 6 and Spring Boot 3 

## Outline 

NOTES: cut

* TODO installing GraalVM and the `native-image` CLI
* (one.mov: cut everything after 13:55 ) getting the bits: Spring Native 0.11.snapshots 
* (one.mov: cut everything after 13:55 ) a reactive, non-blocking http app talking to an embedded SQL DB
* (two.mov) introducing the new AOT engine. You can preflight what its doing with `spring-aot:generate`. `spring.factories`. functional bean registration. it creates a version of the app without running the beans and uses that to inform the generation of hints. we run the context without starting anything. 
* (two.mov) free performance bump even on the JRE:  `-DspringAot=true`
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


## Performance 

Run `measure.sh` with the following syntax in each of the `traditional`, `reactive`, and `batch` modules: 

`measure.sh <MODULE>` 

So, if you were in the `traditional` module, you'd run `measure.sh traditional`. In the `batch` module, you'd run `measure.sh batch`. You get the idea.

It'll capture startup time and RSS for the JRE, AOT, and native versions of the application.

---------------------------
reactive
native
0.061
RSS memory (PID: 12984): 57.8M

jre
1.727
RSS memory (PID: 13028): 472.3M

aot
1.513
RSS memory (PID: 13056): 493.0M

---------------------------
traditional
native
0.155
RSS memory (PID: 14161): 127.2M

jre
3.616
RSS memory (PID: 14188): 649.9M

aot
2.802
RSS memory (PID: 14226): 647.5M

---------------------------
batch
native
0.065
RSS memory (PID: 18382): 83.8M

jre
1.387
RSS memory (PID: 18413): 460.9M

aot
1.066
RSS memory (PID: 18443): 419.0M


