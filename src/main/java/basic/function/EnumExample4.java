package basic.function;

import java.util.EnumSet;

enum Day {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WENSEDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY;
}
public class EnumExample4 {

    // EnumSet
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for(Day d : EnumSet.range(Day.MONDAY, Day.FRIDAY)) {
            sb.append(d).append(" ");
        }

        System.out.println(sb); // MONDAY TUESDAY WENSEDAY THURSDAY FRIDAY

        EnumSet<Day> weekend = EnumSet.of(Day.SATURDAY, Day.SUNDAY);
        System.out.println(weekend); // [SUNDAY, SATURDAY]
    }

}
