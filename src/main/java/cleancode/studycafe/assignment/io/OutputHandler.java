package cleancode.studycafe.assignment.io;

import cleancode.studycafe.assignment.model.StudyCafeLockerPass;
import cleancode.studycafe.assignment.model.StudyCafePass;

import java.util.List;

public interface OutputHandler {

    void showStartMessage();

    void askPassTypeSelection();

    void showPassListForSelection(List<StudyCafePass> passes);

    void askLockerPass(StudyCafeLockerPass lockerPass);

    void showPassOrderSummary(boolean lockerSelection, StudyCafePass selectedPass, StudyCafeLockerPass lockerPass);

    void showSimpleMessage(String message);
}
