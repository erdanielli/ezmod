ezMod
====
Modular design made easy.
---
True modular design consists in breaking your application into JARs, not just packages. For example:
<pre>
<b>myapp-api.jar</b>     --> your (well-tested) business logic goes here.
  \_ src/main/java
                  \_ app.UseCase (concrete class)
                  \_ app.SomeRepository (UseCase colaborator injected at runtime)
  \_ src/test/java
                  \_ app.UseCaseTest (unit test your UseCase mocking the colaborator)
                  
<b>myapp-stubs.jar</b>   --> requires myapp-api.jar at compile-time
  \_ src/main/java
                  \_ app.stubs.SomeRepositoryStubs (Simple stubs for agile development)
                  \_ app.Module --> export(app.SomeRepository.class, new app.stubs.SomeRepositoryStubs())
  \_ src/main/resources
                  \_ META-INF/services/com.github.erdanielli.ezmod.AbstractModule --> app.Module
                  
<b>myapp-jdbc.jar</b>    --> requires myapp-api.jar at compile-time (and probably a few JDBC frameworks)
  \_ src/main/java
                  \_ app.jdbc.SomeRepositoryJdbc (Ask the database)
                  \_ app.Module --> export(app.SomeRepository.class, new app.jdbc.SomeRepository())
  \_ src/main/resources
                  \_ META-INF/services/com.github.erdanielli.ezmod.AbstractModule --> app.Module
                  
<b>myapp-client.jar</b> (bootstrap)
  \_ src/main/java
                  \_ app.Main --> new app.UseCase(Injector.getInstance(app.SomeRepository))
  \_ libs
        \_ myapp-api.jar (mandatory)
        \_ EITHER myapp-stubs.jar OR myapp-jdbc.jar (maven profiles to the rescue)
</pre>  

Only 3 steps required:

1) Create your module
<pre>
public class ModuleA extends com.github.erdanielli.ezmod.<b>AbstractModule</b> {
  @Override
    protected void configure() {
      export(List.class, new ArrayList());
    }
}
</pre>

2) Create the file ...
<pre>
<b>META-INF/services/com.github.erdanielli.ezmod.AbstractModule</b>
</pre>
... with your module's (full) class name inside
<pre>
ModuleA
</pre>

3) Inject it anywhere!
<pre>
public class Main {
  public static void main(String[] args) {
    List emptyArrayList = <b>Injector</b>.getInstance(List.class);
    
    // will throw NoSuchImplementationException
    // Set willFail = Inject.getInstance(Set.class)
   
    Map _null_ = Injector.getInstance(Map.class, true);
  }
}
</pre>

Versions
========
0.1 - <i>in progress</i>
+ Multiple modules with cyclic dependency detection;
+ Servlet listener for eager module loading on initialization

0.2 - Coming soon!
+ Support for Spring-based modules
+ (to be defined)
