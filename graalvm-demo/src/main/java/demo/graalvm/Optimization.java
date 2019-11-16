package demo.graalvm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

/*
%GRAALVM_HOME%\bin\javac src\main\java\demo\graalvm\Optimization.java
%GRAALVM_HOME%\bin\java --vm.Dgraal.Dump=:3 --vm.Dgraal.PrintGraph=Network -cp  src\main\java demo.graalvm.Optimization

https://www.graalvm.org/docs/reference-manual/tools/#ideal-graph-visualizer
set jdkhome variable in etc/idealgraphvisualizer.conf to graalvm dir - it requires java 8
 */
public class Optimization {
    public static void main(String[] args) {
        IntStream.range(0, 10_000)
                .forEach(x -> {
                    System.out.println(negateComplex(2));
                    System.out.println(negateSimple(3));
                    callNegate();
                });
        System.out.println("Press enter to exit ...");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int negateComplex(int input) {
        final int factor = 1 - 2;
        final Integer[] array = new Integer[] { input * factor };
        return array[0];
    }

    private static int negateSimple(int input) {
        return -input;
    }

    private static void callNegate() {
        negateComplex(1);
    }
}
