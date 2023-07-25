package basic.function;

import java.io.BufferedReader;
import java.io.InputStreamReader;

enum Operation {
    PLUS { double eval(double x, double y) { return x + y; } },
    MINUS { double eval(double x, double y) { return x - y; } },
    TIMES { double eval(double x, double y) { return x * y; } },
    DIVIDE { double eval(double x, double y) { return x / y; } };

    // Do arithmetic op represented by this constant
    abstract double eval(double x, double y);
}
public class EnumExample3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        double x = Double.parseDouble(br.readLine());
        double y = Double.parseDouble(br.readLine());

        for(Operation op : Operation.values()) {
            sb.append(String.format("%f %s %f = %f%n", x, op, y, op.eval(x, y)));
        }

        System.out.println(sb);
    }
}
