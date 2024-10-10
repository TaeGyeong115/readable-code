package cleancode.studycafe.assignment.model;

public class StudyCafeSeatPass {

    private final StudyCafePassType passType;
    private final int duration;
    private final int price;
    private final double discountRate;

    private StudyCafeSeatPass(StudyCafePassType passType, int duration, int price, double discountRate) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
        this.discountRate = discountRate;
    }

    public static StudyCafeSeatPass of(StudyCafePassType passType, int duration, int price, double discountRate) {
        return new StudyCafeSeatPass(passType, duration, price, discountRate);
    }

    public StudyCafePassType getPassType() {
        return passType;
    }

    public int getDuration() {
        return duration;
    }

    public int getTotalPrice(StudyCafeLockerPass lockerPass) {
        return price - getDiscountPrice() + (lockerPass != null ? lockerPass.getPrice() : 0);
    }

    public int getDiscountPrice() {
        return (int) (price * discountRate);
    }

    public String display() {
        if (passType == StudyCafePassType.HOURLY) {
            return String.format("%s시간권 - %d원", duration, price);
        }
        if (passType == StudyCafePassType.WEEKLY) {
            return String.format("%s주권 - %d원", duration, price);
        }
        if (passType == StudyCafePassType.FIXED) {
            return String.format("%s주권 - %d원", duration, price);
        }
        return "";
    }

    public boolean isSamePassType(StudyCafePassType studyCafePassType) {
        return passType == studyCafePassType;
    }
}
