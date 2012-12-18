ezMod
====
Modular design made easy.
---

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
