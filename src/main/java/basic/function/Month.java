package basic.function;

public enum Month {
    JANUARY("Jan", 1),
    FEBRUARY("Feb", 2),
    MARCH("Mar", 3),
    APRIL("Apr", 4),
    MAY("May", 5),
    JUNE("Jun", 6),
    JULY("Jul", 7),
    AUGUST("Aug", 7),
    SEPTEMBER("Sept", 7),
    OCTOBER("Oct", 10),
    NOVEMBER("Nov", 11),
    DECEMBER("Dec", 12);

    private final String code;
    private final int number;

    Month(String code, int number) {
        this.code = code;
        this.number = number;
    }

    public String getCode() {
        return code;
    }

    public int getNumber() {
        return number;
    }
}
