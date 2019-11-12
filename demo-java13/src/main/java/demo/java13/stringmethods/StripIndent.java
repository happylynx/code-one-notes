package demo.java13.stringmethods;

// "c:\Program Files\Java\jdk-13.0.1\bin\java.exe" --enable-preview --source 13 src\main\java\demo\java13\stringmethods\StripIndent.java

public class StripIndent {
    public static void main(String[] args) {
        final String input = "    <foo>\r\n"
                + "        <bar>\n"
                + "\t\t\t<baz />\r\n"
                + "        </bar>\n"
                + "\t</foo>";
        final String stripped = input.stripIndent();

        System.out.println("raw");
        System.out.println(stripped);
        System.out.println();

        final String visualized = stripped.replace(' ', '.')
                .replace("\t", "t")
                .replace("\r", "\\r")
                .replace("\n", "\\n\n");

        System.out.println("visualized");
        System.out.println(visualized);
        System.out.println();
    }
}
