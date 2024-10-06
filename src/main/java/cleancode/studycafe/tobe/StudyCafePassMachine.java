package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.config.StudyCafeConfig;
import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.ConsoleInputHandler;
import cleancode.studycafe.tobe.io.ConsoleOutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.studycafe.StudyCafeInitializable;
import cleancode.studycafe.tobe.studycafe.StudyCafeRunable;

import java.util.List;

public class StudyCafePassMachine implements StudyCafeInitializable, StudyCafeRunable {

    private final StudyCafeFileHandler studyCafeFileHandler;
    private final ConsoleInputHandler consoleInputHandler;
    private final ConsoleOutputHandler consoleOutputHandler;
    private List<StudyCafePass> studyCafePasses;

    public StudyCafePassMachine(StudyCafeConfig studyCafeConfig) {
        this.studyCafeFileHandler = studyCafeConfig.getHandler();
        this.consoleInputHandler = studyCafeConfig.getInputHandler();
        this.consoleOutputHandler = studyCafeConfig.getOutputHandler();
    }

    @Override
    public void initializable() {
        this.studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
    }

    @Override
    public void run() {
        try {
            consoleOutputHandler.showStartMessage();
            consoleOutputHandler.askPassTypeSelection();
            StudyCafePassType studyCafePassType = consoleInputHandler.getPassTypeSelectingUserAction();
            calculateStudyCafeCost(studyCafePassType);
        } catch (AppException e) {
            consoleOutputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            consoleOutputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private void calculateStudyCafeCost(StudyCafePassType studyCafePassType) {
        List<StudyCafePass> passes = this.studyCafePasses.stream()
            .filter(studyCafePass -> studyCafePassType == studyCafePass.getPassType())
            .toList();
        consoleOutputHandler.showPassListForSelection(passes);
        StudyCafePass selectedPass = consoleInputHandler.getSelectPass(passes);

        if (checkStudyCafePassType(studyCafePassType, StudyCafePassType.FIXED)) {
            List<StudyCafeLockerPass> lockerPasses = studyCafeFileHandler.readLockerPasses();
            StudyCafeLockerPass lockerPass = lockerPasses.stream()
                .filter(option ->
                    option.getPassType() == selectedPass.getPassType()
                        && option.getDuration() == selectedPass.getDuration()
                )
                .findFirst()
                .orElse(null);

            boolean lockerSelection = false;
            if (lockerPass != null) {
                consoleOutputHandler.askLockerPass(lockerPass);
                lockerSelection = consoleInputHandler.getLockerSelection();
            }
            consoleOutputHandler.showLockerSummary(lockerSelection, selectedPass, lockerPass);
        } else {
            consoleOutputHandler.showPassOrderSummary(selectedPass, null);
        }
    }

    private boolean checkStudyCafePassType(StudyCafePassType passType, StudyCafePassType targetType) {
        return passType == targetType;
    }

}
