package cleancode.studycafe.tobe.model.pass.order;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafePassOrderTest {

    @Test
    @DisplayName("1인권 고정석과 락커의 총 금액을 계산할 수 있다.")
    void calculateFixedPassTotalPrice() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 250000, 0.1);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 10000);

        // when
        StudyCafePassOrder passOrder = StudyCafePassOrder.of(seatPass, lockerPass);

        //then
        assertThat(passOrder).isNotNull();
        assertThat(passOrder.getTotalPrice()).isEqualTo(235000);
    }

    @Test
    @DisplayName("1인권 고정석이 아니면 락커를 사용할 수 없다.")
    void lockerCannotBeUsedIfNotFixedPass() {
        // given

        // when

        //then

    }

    @Test
    @DisplayName("주 이용권 총 금액을 계산할 수 있다.")
    void calculateWeeklyPassTotalPrice() {
        // given

        // when

        //then

    }

    @Test
    @DisplayName("시간 이용권 총 금액을 계산할 수 있다.")
    void calculateHourlyPassTotalPrice() {
        // given

        // when

        //then

    }

}
