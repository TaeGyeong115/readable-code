package cleancode.studycafe.tobe.model.pass;

import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudyCafeSeatPassTest {

    @Test
    @DisplayName("1인 지정석권은 라커 사용여부를 결정할 수 있다.")
    void fixedPassCanUsageLocker() {
        // given
        StudyCafeSeatPass pass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 0, 0, 0);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 0, 0);

        // when & then
        Assertions.assertThat(pass.cannotUseLocker()).isFalse();
        Assertions.assertThat(pass.isSameDurationType(lockerPass)).isTrue();
    }

    @Test
    @DisplayName("주 단위 이용권은 라커를 사용할 수 없다.")
    void weeklyPassCanUsageLocker() {
        // given
        StudyCafeSeatPass pass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 0, 0, 0);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.WEEKLY, 0, 0);

        // when & then
        Assertions.assertThat(pass.cannotUseLocker()).isTrue();
        Assertions.assertThat(pass.isSameDurationType(lockerPass)).isTrue();
    }

    @Test
    @DisplayName("시간 단위 이용권은 라커를 사용할 수 없다.")
    void hourlyPassCanUsageLocker() {
        // given
        StudyCafeSeatPass pass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 0, 0, 0);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.HOURLY, 0, 0);

        // when & then
        Assertions.assertThat(pass.cannotUseLocker()).isTrue();
        Assertions.assertThat(pass.isSameDurationType(lockerPass)).isTrue();
    }
}
