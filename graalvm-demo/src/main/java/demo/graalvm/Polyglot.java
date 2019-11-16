package demo.graalvm;

// part of graal vm, javadoc https://www.graalvm.org/sdk/javadoc/
import org.graalvm.polyglot.*;
import org.graalvm.polyglot.proxy.*;

/*
%GRAALVM_HOME%\bin\javac src\main\java\demo\graalvm\Polyglot.java
%GRAALVM_HOME%\bin\java -cp src\main\java demo.graalvm.Polyglot

https://www.graalvm.org/docs/reference-manual/embed/
*/

public class Polyglot {
    public static void main(String[] args) {
        System.out.println("Hello Java!");
        try (Context context = Context.create()) {
            context.eval("js", "print('Hello JavaScript!');");
        }
    }
}