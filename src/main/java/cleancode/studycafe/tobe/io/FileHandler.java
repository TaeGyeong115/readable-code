package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;

import java.util.List;

public interface FileHandler {

    String PASS_PATH = "src/main/resources/cleancode/studycafe/pass-list.csv";
    String LOCKER_PATH = "src/main/resources/cleancode/studycafe/locker.csv";
    String REGEX = ",";

    List<StudyCafePass> readStudyCafePasses();

    List<StudyCafeLockerPass> readLockerPasses();
}
