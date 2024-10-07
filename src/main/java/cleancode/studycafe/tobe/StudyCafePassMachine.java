package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.config.StudyCafeConfig;
import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.FileHandler;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.studycafe.StudyCafeInitializable;
import cleancode.studycafe.tobe.studycafe.StudyCafeRunable;

import java.util.List;
import java.util.Map;

public class StudyCafePassMachine implements StudyCafeInitializable, StudyCafeRunable {

    private final FileHandler fileHandler;
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private Map<String, List<StudyCafePass>> studyCafePasses;
    private Map<String, List<StudyCafeLockerPass>> lockerPasses;

    public StudyCafePassMachine(StudyCafeConfig studyCafeConfig) {
        this.fileHandler = studyCafeConfig.getFileHandler();
        this.inputHandler = studyCafeConfig.getInputHandler();
        this.outputHandler = studyCafeConfig.getOutputHandler();
    }

    @Override
    public void initializable() {
        this.studyCafePasses = fileHandler.readStudyCafePasses();
        this.lockerPasses = fileHandler.readLockerPasses();
    }

    @Override
    public void run() {
        try {
            outputHandler.showStartMessage();

            StudyCafePassType studyCafePassType = getPassTypeFromUser();
            calculateStudyCafeCost(studyCafePassType);
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafePassType getPassTypeFromUser() {
        outputHandler.askPassTypeSelection();
        return inputHandler.getPassTypeSelectingUserAction();
    }

    private void calculateStudyCafeCost(StudyCafePassType studyCafePassType) {
        List<StudyCafePass> passes = this.studyCafePasses.get(studyCafePassType.name());
        outputHandler.showPassListForSelection(passes);
        StudyCafePass selectedPass = inputHandler.getSelectPass(passes);

        if (checkStudyCafePassType(studyCafePassType, StudyCafePassType.FIXED)) {
            List<StudyCafeLockerPass> lockerPasses = this.lockerPasses.get(studyCafePassType.name());
            StudyCafeLockerPass lockerPass = (StudyCafeLockerPass) lockerPasses.stream().filter(pass -> pass.getDuration() == selectedPass.getDuration());
            outputHandler.askLockerPass(lockerPass);
            boolean lockerSelection = inputHandler.getLockerSelection();
            outputHandler.showPassOrderSummary(lockerSelection, selectedPass, lockerPass);
        } else {
            outputHandler.showPassOrderSummary(false, selectedPass, null);
        }
    }

    private boolean checkStudyCafePassType(StudyCafePassType passType, StudyCafePassType targetType) {
        return passType == targetType;
    }

}
