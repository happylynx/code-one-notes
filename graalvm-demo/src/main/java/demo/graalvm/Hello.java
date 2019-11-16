package demo.graalvm;

/*

compilation:
%GRAALVM_HOME%\bin\javac src\main\java\demo\graalvm\Hello.java

run in jvm:
%GRAALVM_HOME%\bin\java -cp  src\main\java demo.graalvm.Hello

compile as s native image (on Windows "Windows 7 SDK Command prompt" is required):
%GRAALVM_HOME%\bin\native-image.cmd -cp src\main\java demo.graalvm.Hello hello

 */

public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello world");
    }
}
