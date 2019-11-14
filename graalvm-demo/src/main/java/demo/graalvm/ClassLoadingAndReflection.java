package demo.graalvm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/*
c:\Users\jniederm\opt\graalvm\graalvm-ce-19.2.1\bin\javac src\main\java\demo\graalvm\ClassLoadingAndReflection.java src\main\java\demo\graalvm\Hello.java
c:\Users\jniederm\opt\graalvm\graalvm-ce-19.2.1\bin\java -cp src\main\java;src\main\resources demo.graalvm.ClassLoadingAndReflection
c:\Users\jniederm\opt\graalvm\graalvm-ce-19.2.1\bin\java -agentlib:native-image-agent=native-image-config -cp src\main\java;src\main\resources demo.graalvm.ClassLoadingAndReflection
c:\Users\jniederm\opt\graalvm\graalvm-ce-19.2.1\bin\native-image.cmd -H:ConfigurationFileDirectories=native-image-config  -cp src\main\java;src\main\resources -H:Log=registerResource -H:IncludeResources=.*\.txt  demo.graalvm.ClassLoadingAndReflection classLoadingAndReflection
 */
public class ClassLoadingAndReflection {
    public static void main(String[] args) {
        System.out.println("loaded class: " + loadClass());
        System.out.println("resource: " + loadResource());
        System.out.println("reflection: " + reflection());

    }

    private static String loadResource() {
        final ClassLoader classLoader = ClassLoadingAndReflection.class.getClassLoader();
        try (final BufferedReader br = new BufferedReader(new InputStreamReader(
                classLoader.getResourceAsStream("demo/graalvm/hello.txt")))) {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String loadClass() {
        try {
            return ClassLoadingAndReflection.class.getClassLoader().loadClass("demo.graalvm.Hello")
                    .getCanonicalName();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static String reflection() {
        try {
            final Method method = ClassLoadingAndReflection.class.getDeclaredMethod("getHello");
            return (String) method.invoke(null);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getHello() {
        return "Hello";
    }
}
