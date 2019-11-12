package demo.java13.stringmethods;

// "c:\Program Files\Java\jdk-13.0.1\bin\java.exe" --enable-preview --source 13 src\main\java\demo\java13\stringmethods\TranslateEscapes.java

public class TranslateEscapes {
    public static void main(String[] args) {
        final String original = "a\\t\\tb";
        System.out.println("original         " + original);
        System.out.println("translated       " + original.translateEscapes());
        System.out.println("translated twice " + original.translateEscapes().translateEscapes());
    }
}
