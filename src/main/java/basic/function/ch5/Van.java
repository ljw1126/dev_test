package basic.function.ch5;

public class Van extends Car {
    public Van(String name, String brand) {
        super(name, brand);
    }

    @Override
    public void drive() {
        System.out.println(String.format("Driving a Van %s name %s", name, brand));
    }
}
