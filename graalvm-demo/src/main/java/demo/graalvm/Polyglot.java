package demo.graalvm;

// part of graal vm, javadoc https://www.graalvm.org/sdk/javadoc/
import org.graalvm.polyglot.*;
import org.graalvm.polyglot.proxy.*;

/*
c:\Users\jniederm\opt\graalvm\graalvm-ce-19.2.1\bin\javac src\main\java\demo\graalvm\Polyglot.java
c:\Users\jniederm\opt\graalvm\graalvm-ce-19.2.1\bin\java -cp src\main\java demo.graalvm.Polyglot

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