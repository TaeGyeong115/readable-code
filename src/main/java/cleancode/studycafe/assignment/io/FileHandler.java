package cleancode.studycafe.assignment.io;

import cleancode.studycafe.assignment.model.StudyCafeLockerPass;
import cleancode.studycafe.assignment.model.StudyCafePass;

import java.util.List;
import java.util.Map;

public interface FileHandler {

    String PASS_PATH = "src/main/resources/cleancode/studycafe/pass-list.csv";
    String LOCKER_PATH = "src/main/resources/cleancode/studycafe/locker.csv";
    String REGEX = ",";

    Map<String, List<StudyCafePass>> readStudyCafePasses();

    Map<String, List<StudyCafeLockerPass>> readLockerPasses();
}
