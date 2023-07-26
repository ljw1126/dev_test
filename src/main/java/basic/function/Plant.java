package basic.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static basic.function.Plant.LifeCycle.ANNUAL;
import static basic.function.Plant.LifeCycle.PERENNIAL;

public class Plant {
    enum LifeCycle { ANNUAL, PERENNIAL, BIENNIAL }

    private final String name;
    private final LifeCycle lifeCycle;

    public Plant(String name, LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    public String getName() {
        return name;
    }

    public LifeCycle getLifeCycle() {
        return lifeCycle;
    }

    @Override
    public String toString() {
        return name;
    }

    private static List<Plant> garden = new ArrayList<>();

    static {
        garden.add(new Plant("장미", ANNUAL));
        garden.add(new Plant("튤립", ANNUAL));
        garden.add(new Plant("해바라기", ANNUAL));
        garden.add(new Plant("백합", PERENNIAL));
    }

    public static List<Plant> garden() {
        return new ArrayList<>(garden);
    }

    // {ANNUAL=[해바라기, 장미, 튤립], PERENNIAL=[백합], BIENNIAL=[]}
    private static Map<LifeCycle, Set<Plant>> plantsByLifeCycle = new EnumMap<>(LifeCycle.class);

    static {
        for(LifeCycle lc : LifeCycle.values()) {
            plantsByLifeCycle.put(lc, new HashSet<>());
        }

        for(Plant plant : garden) {
            plantsByLifeCycle.get(plant.lifeCycle).add(plant);
        }
    }
}
