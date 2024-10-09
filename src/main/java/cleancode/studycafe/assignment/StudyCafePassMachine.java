package cleancode.studycafe.assignment;

import cleancode.studycafe.assignment.exception.AppException;
import cleancode.studycafe.assignment.io.FileHandler;
import cleancode.studycafe.assignment.io.InputHandler;
import cleancode.studycafe.assignment.io.OutputHandler;
import cleancode.studycafe.assignment.model.StudyCafeLockerPass;
import cleancode.studycafe.assignment.model.StudyCafePass;
import cleancode.studycafe.assignment.model.StudyCafePassType;
import cleancode.studycafe.assignment.studycafe.StudyCafeInitializable;
import cleancode.studycafe.assignment.studycafe.StudyCafeRunable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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

        if (checkStudyCafePassType(studyCafePassType)) {
            List<StudyCafeLockerPass> lockerPasses = this.lockerPasses.get(studyCafePassType.name());
            Optional<StudyCafeLockerPass> lockerPass = lockerPasses.stream().filter(pass -> pass.getDuration() == selectedPass.getDuration()).findFirst();
            if (lockerPass.isPresent()) {
            outputHandler.askLockerPass(lockerPass.get());
            boolean lockerSelection = inputHandler.getLockerSelection();
            outputHandler.showPassOrderSummary(lockerSelection, selectedPass, lockerPass.get());
            }
        } else {
            outputHandler.showPassOrderSummary(false, selectedPass, null);
        }
    }

    private boolean checkStudyCafePassType(StudyCafePassType passType) {
        return passType == StudyCafePassType.FIXED;
    }

}
