package demo.java13;

// "c:\Program Files\Java\jdk-13.0.1\bin\java.exe" --enable-preview --source 13 src\main\java\demo\java13\SwitchStatement.java

// "c:\Program Files\Java\jdk-13.0.1\bin\javac.exe" --enable-preview --source 13 -Xlint:preview src\main\java\demo\java13\SwitchStatement.java
// generates warnings
// "c:\Program Files\Java\jdk-13.0.1\bin\java.exe" --enable-preview -cp src\main\java demo.java13.SwitchStatement

public class SwitchStatement {
    public static void main(String[] args) {
        System.out.println(oldSwitch());
        System.out.println(newArrowSwitch());
        System.out.println(newYieldSwitch());
    }

    private static int newYieldSwitch() {
        final String word = "hello";
        return switch (word) {
            case "hello":
                System.out.println("side effect new yield");
            case "world":
                yield 42;
            case "foo":
                yield  -1;
            default:
                yield 0;
        };
    }

    private static int newArrowSwitch() {
        final String word = "hello";
        return switch (word) {
            case "hello", "world" -> 42;
            case "foo" -> -1;
            default -> 0;
        };
    }

    private static int oldSwitch() {
        final String word = "hello";
        switch (word) {
            case "hello":
                System.out.println("side effect old");
            case "hi":
                return 42;
            case "foo":
                break;
            default:
                return 0;
        }
        return -1;
    }
}
