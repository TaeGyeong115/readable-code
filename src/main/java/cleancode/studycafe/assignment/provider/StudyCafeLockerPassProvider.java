package cleancode.studycafe.assignment.provider;

import cleancode.studycafe.assignment.model.StudyCafeLockerPasses;

public interface StudyCafeLockerPassProvider {

    String LOCKER_CSV_PATH = "src/main/resources/cleancode/studycafe/locker.csv";

    StudyCafeLockerPasses getLockerPasses();
}
