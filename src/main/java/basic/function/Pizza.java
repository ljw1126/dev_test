package basic.function;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public class Pizza {
    private PizzaStatus pizzaStatus;

    public Pizza(PizzaStatus pizzaStatus) {
        this.pizzaStatus = pizzaStatus;
    }

    private void setPizzaStatus(PizzaStatus pizzaStatus) {
        this.pizzaStatus = pizzaStatus;
    }

    public PizzaStatus getPizzaStatus() {
        return pizzaStatus;
    }


    public boolean isDeliverable() {
        return this.pizzaStatus.isReady();
    }

    public void printTimeToDeliver() {
        System.out.println("Time to delivery is " + this.pizzaStatus.getTimeToDelivery());
    }

    // delivered 아닌 상태
    private static EnumSet<PizzaStatus> undeliveredPizzaStatuses = EnumSet.of(PizzaStatus.ORDERED, PizzaStatus.READY);

    public static List<Pizza> getAllUndeliveredPizzas(List<Pizza> input) {
        return input.stream()
                .filter(s -> undeliveredPizzaStatuses.contains(s.getPizzaStatus()))
                .collect(Collectors.toList());
    }

    public void deliver() {
        if(isDeliverable()) {
            PizzaDeliverySystemConfiguration.getInstance().getDeliveryStrategy().deliver(this);

            this.setPizzaStatus(PizzaStatus.DELIVERED);
        }
    }

    public static EnumMap<PizzaStatus, List<Pizza>> groupPizzaByStatus(List<Pizza> pizzaList) {
        /*
        EnumMap<PizzaStatus, List<Pizza>> pzByStatusMap
                = new EnumMap<>(PizzaStatus.class); // EnumMap 생성자에는 키가 되는 클래스 타입 토큰 필요

        for(Pizza pizza : pizzaList) {
            PizzaStatus status = pizza.getPizzaStatus();

            pzByStatusMap.putIfAbsent(status, new ArrayList<>());
            pzByStatusMap.get(status).add(pizza);
        }

        return pzByStatusMap;
        */

        // TODO. 제네릭 해석 안됨
        return pizzaList.stream().collect(Collectors.groupingBy(
                Pizza::getPizzaStatus, () -> new EnumMap<>(PizzaStatus.class), Collectors.toList()
        ));
    }
}
