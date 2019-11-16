package demo.graalvm;

import java.time.Instant;

/*
%GRAALVM_HOME%\bin\javac src\main\java\demo\graalvm\StaticInitialization.java
%GRAALVM_HOME%\bin\java -cp  src\main\java demo.graalvm.StaticInitialization
%GRAALVM_HOME%\bin\native-image.cmd --initialize-at-build-time=java --initialize-at-build-time=demo -cp src\main\java demo.graalvm.StaticInitialization staticInitialization
 */

public class StaticInitialization {
    private static String name = "world " + Instant.now().toString();
    public static void main(String[] args) {
        System.out.println("Hello " + name);
    }
}
