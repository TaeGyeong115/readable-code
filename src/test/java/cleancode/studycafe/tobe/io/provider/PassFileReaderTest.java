package cleancode.studycafe.tobe.io.provider;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPasses;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPasses;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class PassFileReaderTest {

    @Test
    @DisplayName("pass-list.csv 파일을 읽어오면 좌석권 목록에 담긴다..")
    void getSeatPasses() {
        // given
        SeatPassFileReader fileReader = new SeatPassFileReader();
        List<StudyCafeSeatPass> fixedPassList = createFixedPassList();
        List<StudyCafeSeatPass> weeklyPassList = createWeeklyPassList();
        List<StudyCafeSeatPass> hourlyPassList = createHourlyPassList();

        // when
        StudyCafeSeatPasses seatPasses = fileReader.getSeatPasses();
        List<StudyCafeSeatPass> fixedPassBy = seatPasses.findPassBy(StudyCafePassType.FIXED);
        List<StudyCafeSeatPass> weeklyPassBy = seatPasses.findPassBy(StudyCafePassType.WEEKLY);
        List<StudyCafeSeatPass> hourlyPassBy = seatPasses.findPassBy(StudyCafePassType.HOURLY);

        //then
        assertThat(fixedPassBy).containsAll(fixedPassList);
        assertThat(weeklyPassBy).containsAll(weeklyPassList);
        assertThat(hourlyPassBy).containsAll(hourlyPassList);
    }

    @Test
    @DisplayName("locker.csv 파일을 읽어오면 락커 사용권 목록에 담긴다.")
    void getLockerPasses() {
        // given
        StudyCafePassType passType = StudyCafePassType.FIXED;
        LockerPassFileReader fileReader = new LockerPassFileReader();

        StudyCafeLockerPass lockerPass4 = StudyCafeLockerPass.of(passType, 4, 10000);
        StudyCafeLockerPass lockerPass12 = StudyCafeLockerPass.of(passType, 12, 30000);

        StudyCafeSeatPass seatPass4 = StudyCafeSeatPass.of(passType, lockerPass4.getDuration(), lockerPass4.getPrice(), 0);
        StudyCafeSeatPass seatPass12 = StudyCafeSeatPass.of(passType, lockerPass12.getDuration(), lockerPass12.getPrice(), 0);

        // when
        StudyCafeLockerPasses lockerPasses = fileReader.getLockerPasses();
        Optional<StudyCafeLockerPass> lockerPassBy4 = lockerPasses.findLockerPassBy(seatPass4);
        Optional<StudyCafeLockerPass> lockerPassBy12 = lockerPasses.findLockerPassBy(seatPass12);

        // then
        assertThat(lockerPassBy4).isPresent();
        assertThat(lockerPassBy4.get().isSampePassType(passType)).isTrue();
        assertThat(lockerPassBy4.get().getDuration()).isEqualTo(lockerPass4.getDuration());
        assertThat(lockerPassBy4.get().getPrice()).isEqualTo(lockerPass4.getPrice());

        assertThat(lockerPassBy12).isPresent();
        assertThat(lockerPassBy12.get().isSampePassType(passType)).isTrue();
        assertThat(lockerPassBy12.get().getDuration()).isEqualTo(lockerPass12.getDuration());
        assertThat(lockerPassBy12.get().getPrice()).isEqualTo(lockerPass12.getPrice());
    }

    private static List<StudyCafeSeatPass> createHourlyPassList() {
        StudyCafeSeatPass hourlySeatPass2 = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 2, 4000, 0.0);
        StudyCafeSeatPass hourlySeatPass4 = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 4, 6500, 0.0);
        StudyCafeSeatPass hourlySeatPass6 = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 6, 9000, 0.0);
        StudyCafeSeatPass hourlySeatPass8 = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 8, 11000, 0.0);
        StudyCafeSeatPass hourlySeatPass10 = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 10, 12000, 0.0);
        StudyCafeSeatPass hourlySeatPass12 = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 12, 13000, 0.0);
        List<StudyCafeSeatPass> hourlyPassList = new ArrayList<>();
        hourlyPassList.add(hourlySeatPass2);
        hourlyPassList.add(hourlySeatPass4);
        hourlyPassList.add(hourlySeatPass6);
        hourlyPassList.add(hourlySeatPass8);
        hourlyPassList.add(hourlySeatPass10);
        hourlyPassList.add(hourlySeatPass12);
        return hourlyPassList;
    }

    private static List<StudyCafeSeatPass> createWeeklyPassList() {
        StudyCafeSeatPass weeklySeatPass1 = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 1, 60000, 0.0);
        StudyCafeSeatPass weeklySeatPass2 = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 2, 100000, 0.1);
        StudyCafeSeatPass weeklySeatPass3 = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 3, 130000, 0.1);
        StudyCafeSeatPass weeklySeatPass4 = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 4, 150000, 0.1);
        StudyCafeSeatPass weeklySeatPass12 = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 12, 400000, 0.15);
        List<StudyCafeSeatPass> weeklyPassList = new ArrayList<>();
        weeklyPassList.add(weeklySeatPass1);
        weeklyPassList.add(weeklySeatPass2);
        weeklyPassList.add(weeklySeatPass3);
        weeklyPassList.add(weeklySeatPass4);
        weeklyPassList.add(weeklySeatPass12);
        return weeklyPassList;
    }

    private static List<StudyCafeSeatPass> createFixedPassList() {
        StudyCafeSeatPass fixedSeatPass4 = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 250000, 0.1);
        StudyCafeSeatPass fixedSeatPass12 = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 700000, 0.15);
        List<StudyCafeSeatPass> fixedPassList = new ArrayList<>();
        fixedPassList.add(fixedSeatPass4);
        fixedPassList.add(fixedSeatPass12);
        return fixedPassList;
    }
}
