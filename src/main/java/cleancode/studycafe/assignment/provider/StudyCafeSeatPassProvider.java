package cleancode.studycafe.assignment.provider;

import cleancode.studycafe.assignment.model.StudyCafeSeatPasses;

public interface StudyCafeSeatPassProvider {

    String SEAT_CSV_PATH = "src/main/resources/cleancode/studycafe/pass-list.csv";

    StudyCafeSeatPasses getSeatPasses();
}
