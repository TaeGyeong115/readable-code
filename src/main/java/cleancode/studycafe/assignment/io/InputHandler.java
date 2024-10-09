package cleancode.studycafe.assignment.io;

import cleancode.studycafe.assignment.model.StudyCafePass;
import cleancode.studycafe.assignment.model.StudyCafePassType;

import java.util.List;

public interface InputHandler {

    StudyCafePassType getPassTypeSelectingUserAction();

    StudyCafePass getSelectPass(List<StudyCafePass> passes);

    boolean getLockerSelection();
}
