package basic.function;

import java.io.BufferedReader;
import java.io.InputStreamReader;

enum Planet {
    MERCURY (3.303e+23, 2.4397e6),
    VENUS   (4.869e+24, 6.0518e6),
    EARTH   (5.976e+24, 6.37814e6),
    MARS    (6.421e+23, 3.3972e6),
    JUPITER (1.9e+27,   7.1492e7),
    SATURN  (5.688e+26, 6.0268e7),
    URANUS  (8.686e+25, 2.5559e7),
    NEPTUNE (1.024e+26, 2.4746e7),
    PLUTO   (1.27e+22,  1.137e6);

    private final double mass; // 질량 (단위: 킬로그램)
    private final double radius; // 반지름 (단위: 미터)

    private Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
    }

    public double mass() { return mass; }
    public double radius() { return radius; }

    // 중력 상수  (m^3 / kg s^2)
    public static final double G = 6.67300E-11;

    public double surfaceGravity() {
        return G * mass / (radius * radius);
    }

    public double surfaceWeight(double otherMass) {
        return otherMass * surfaceGravity(); // F = ma
    }
}

public class EnumExample2 {

    // 입력받은 지구에서의 무게가 다른 행성에서 얼마나 하는지 확인
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double earthWeight = Double.parseDouble(br.readLine());
        double mass = earthWeight/Planet.EARTH.surfaceGravity();

        StringBuilder sb = new StringBuilder();
        for(Planet p : Planet.values()) {
            sb.append(String.format("%s 에서의 무게는 %f%n", p, p.surfaceWeight(mass)));
        }

        System.out.println(sb);
    }

}
