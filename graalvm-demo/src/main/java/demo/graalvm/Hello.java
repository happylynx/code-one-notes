package demo.graalvm;

/*

compilation:
c:\Users\jniederm\opt\graalvm\graalvm-ce-19.2.1\bin\javac src\main\java\demo\graalvm\Hello.java

run in jvm:
c:\Users\jniederm\opt\graalvm\graalvm-ce-19.2.1\bin\java -cp  src\main\java demo.graalvm.Hello

compile as s native image (on Windows "Windows 7 SDK Command prompt" is required):
c:\Users\jniederm\opt\graalvm\graalvm-ce-19.2.1\bin\native-image.cmd -cp src\main\java demo.graalvm.Hello hello

 */

public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello world");
    }
}
