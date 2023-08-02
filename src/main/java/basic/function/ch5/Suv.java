package basic.function.ch5;

public class Suv extends Car{
    public Suv(String name, String brand) {
        super(name, brand);
    }

    @Override
    public void drive() {
        System.out.println(String.format("Driving a Suv %s name %s", name, brand));
    }
}
