package basic.function.ch5;

public class Sedan extends Car{

    public Sedan(String name, String brand) {
        super(name, brand);
    }

    @Override
    public void drive() {
        System.out.println(String.format("Driving a sedan %s name %s", name, brand));
    }
}
