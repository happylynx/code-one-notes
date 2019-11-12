package demo.java13;

// "c:\Program Files\Java\jdk-13.0.1\bin\javac.exe" src\main\java\demo\java13\ClassDataSharing.java

// create archive
// "c:\Program Files\Java\jdk-13.0.1\bin\java.exe" -XX:ArchiveClassesAtExit=CDS.jsa -cp src\main\java demo.java13.ClassDataSharing

// use archive
// "c:\Program Files\Java\jdk-13.0.1\bin\java.exe" -XX:SharedArchiveFile=CDS.jsa -cp src\main\java demo.java13.ClassDataSharing

// in bash
//  time /c/Program\ Files/Java/jdk-13.0.1/bin/java -Xshare:off -cp src/main/java demo.java13.ClassDataSharing

public class ClassDataSharing {

    static {
        System.out.println("static block");
    }

    private static int counter = 0;

    public static void main(String[] args) {
        counter++;
        System.out.println(String.format("Main executed %s time(s).", counter));
    }
}
