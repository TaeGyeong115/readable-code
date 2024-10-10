package cleancode.studycafe.assignment.model;

import java.util.List;
import java.util.Optional;

public class StudyCafeLockerPasses {

    private final List<StudyCafeLockerPass> passes;

    public StudyCafeLockerPasses(List<StudyCafeLockerPass> passes) {
        this.passes = passes;
    }

    public static StudyCafeLockerPasses of(List<StudyCafeLockerPass> passes) {
        return new StudyCafeLockerPasses(passes);
    }

    public Optional<StudyCafeLockerPass> getPass(StudyCafeSeatPass studyCafeSeatPass) {
        return passes.stream().filter(pass -> pass.isSamePassType(studyCafeSeatPass)).findFirst();
    }
}
