package basic.function;

public enum PizzaDeliverySystemConfiguration {
    INSTANCE;

    PizzaDeliverySystemConfiguration() {
        // Initialization configuration which involves
        // overriding defaults like delivery strategy
    }

    public static PizzaDeliverySystemConfiguration getInstance() {
        return INSTANCE;
    }

    private PizzaDeliveryStrategy deliveryStrategy;

    public PizzaDeliveryStrategy getDeliveryStrategy() {
        return deliveryStrategy;
    }

    public void setDeliveryStrategy(PizzaDeliveryStrategy deliveryStrategy) {
        this.deliveryStrategy = deliveryStrategy;
    }
}
