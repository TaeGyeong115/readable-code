package cleancode.studycafe.assignment.model;

import java.util.List;
import java.util.stream.Stream;

public class StudyCafeSeatPasses {

    private final List<StudyCafeSeatPass> passes;

    public StudyCafeSeatPasses(List<StudyCafeSeatPass> passes) {
        this.passes = passes;
    }

    public static StudyCafeSeatPasses of(List<StudyCafeSeatPass> passes) {
        return new StudyCafeSeatPasses(passes);
    }

    public List<StudyCafeSeatPass> getPass(StudyCafePassType studyCafePassType) {
        return passes.stream().filter(pass -> pass.isSamePassType(studyCafePassType)).toList();
    }
}
