package cleancode.studycafe.assignment;

import cleancode.studycafe.assignment.exception.AppException;
import cleancode.studycafe.assignment.io.IOHandler;
import cleancode.studycafe.assignment.model.*;
import cleancode.studycafe.assignment.provider.StudyCafeLockerPassProvider;
import cleancode.studycafe.assignment.provider.StudyCafeSeatPassProvider;
import cleancode.studycafe.assignment.studycafe.StudyCafeInitializable;
import cleancode.studycafe.assignment.studycafe.StudyCafeRunable;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine implements StudyCafeInitializable, StudyCafeRunable {

    private final IOHandler ioHandler;
    private final StudyCafeSeatPassProvider seatPassProvider;
    private final StudyCafeLockerPassProvider lockerPassProvider;

    public StudyCafePassMachine(IOHandler ioHandler, StudyCafeSeatPassProvider seatPassProvider, StudyCafeLockerPassProvider lockerPassProvider) {
        this.ioHandler = ioHandler;
        this.seatPassProvider = seatPassProvider;
        this.lockerPassProvider = lockerPassProvider;
    }

    @Override
    public void initializable() {
        seatPassProvider.getSeatPasses();
        lockerPassProvider.getLockerPasses();
    }

    @Override
    public void run() {
        try {
            ioHandler.showStartMessage();
            StudyCafePassType studyCafePassType = getPassTypeFromUser();
            calculateStudyCafeCost(studyCafePassType);
        } catch (AppException e) {
            ioHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            ioHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafePassType getPassTypeFromUser() {
        return ioHandler.askPassTypeSelection();
    }

    private void calculateStudyCafeCost(StudyCafePassType studyCafePassType) {
        StudyCafeSeatPasses seatPasses = seatPassProvider.getSeatPasses();
        List<StudyCafeSeatPass> seatPass = seatPasses.getPass(studyCafePassType);
        StudyCafeSeatPass selectedPass = ioHandler.askPassListForSelection(seatPass);

        if (checkStudyCafePassType(studyCafePassType)) {
            StudyCafeLockerPasses lockerPasses = lockerPassProvider.getLockerPasses();
            Optional<StudyCafeLockerPass> lockerPass = lockerPasses.getPass(selectedPass);
            lockerPass.ifPresent(studyCafeLockerPass -> ioHandler.askLockerPass(studyCafeLockerPass, studyCafeLockerPass, selectedPass));
        } else {
            ioHandler.showPassOrderSummary(false, selectedPass);
        }
    }

    private boolean checkStudyCafePassType(StudyCafePassType passType) {
        return passType == StudyCafePassType.FIXED;
    }
}
