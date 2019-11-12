package demo.java13;

// "c:\Program Files\Java\jdk-13.0.1\bin\java.exe" --enable-preview --source 13 src\main\java\demo\java13\MultilineString.java

public class MultilineString {
    public static void main(String[] args) {
        tripleDoubleQuote();
    }

    private static void tripleDoubleQuote() {
        final String string = """
            <foo>
                <bar>Hello world</bar>
            </foo>
        """;
        System.out.println(string);
    }
}
