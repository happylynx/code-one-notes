package demo.graalvm;

import java.time.Instant;

/*
c:\Users\jniederm\opt\graalvm\graalvm-ce-19.2.1\bin\javac src\main\java\demo\graalvm\StaticInitialization.java
c:\Users\jniederm\opt\graalvm\graalvm-ce-19.2.1\bin\java -cp  src\main\java demo.graalvm.StaticInitialization
c:\Users\jniederm\opt\graalvm\graalvm-ce-19.2.1\bin\native-image.cmd --initialize-at-build-time=java --initialize-at-build-time=demo -cp src\main\java demo.graalvm.StaticInitialization staticInitialization
 */

public class StaticInitialization {
    private static String name = "world " + Instant.now().toString();
    public static void main(String[] args) {
        System.out.println("Hello " + name);
    }
}
