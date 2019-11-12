package demo.java13.stringmethods;

// "c:\Program Files\Java\jdk-13.0.1\bin\java.exe" --enable-preview --source 13 src\main\java\demo\java13\stringmethods\Formatted.java

public class Formatted {
    public static void main(String[] args) {
        final String template = "Hello %s";
        // old
        System.out.println(String.format(template, "world"));
        // new
        System.out.println(template.formatted("world"));
    }
}
