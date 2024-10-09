package cleancode.studycafe.assignment.model;

public enum StudyCafePassType {

    HOURLY(1, "시간 단위 이용권"),
    WEEKLY(2, "주 단위 이용권"),
    FIXED(3, "1인 고정석");

    private final int number;
    private final String description;

    StudyCafePassType(int number, String description) {
        this.number = number;
        this.description = description;
    }

}
