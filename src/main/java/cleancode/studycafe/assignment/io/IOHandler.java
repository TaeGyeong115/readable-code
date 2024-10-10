package cleancode.studycafe.assignment.io;

import cleancode.studycafe.assignment.model.StudyCafeLockerPass;
import cleancode.studycafe.assignment.model.StudyCafeSeatPass;
import cleancode.studycafe.assignment.model.StudyCafePassType;

import java.util.List;

public class IOHandler {

    private final OutputHandler outputHandler;
    private final InputHandler inputHandler;

    public IOHandler() {
        this.outputHandler = new OutputHandler();
        this.inputHandler = new InputHandler();
    }

    public void showStartMessage() {
        outputHandler.showStartMessage();
    }

    public void showSimpleMessage(String message) {
        outputHandler.showSimpleMessage(message);
    }

    public StudyCafePassType askPassTypeSelection() {
        outputHandler.askPassTypeSelection();
        return inputHandler.getPassTypeSelectingUserAction();
    }

    public StudyCafeSeatPass askPassListForSelection(List<StudyCafeSeatPass> passes) {
        outputHandler.showPassListForSelection(passes);
        return inputHandler.getSelectPass(passes);
    }

    public void askLockerPass(StudyCafeLockerPass studyCafeLockerPass, StudyCafeLockerPass lockerPass, StudyCafeSeatPass selectedPass) {
        outputHandler.askLockerPass(lockerPass);
        boolean lockerSelection = inputHandler.getLockerSelection();
        outputHandler.showPassOrderSummary(lockerSelection, selectedPass, lockerPass);
    }

    public void showPassOrderSummary(boolean b, StudyCafeSeatPass selectedPass) {
        outputHandler.showPassOrderSummary(b, selectedPass, null);
    }
}
