package cleancode.studycafe.assignment;

import cleancode.studycafe.assignment.io.IOHandler;
import cleancode.studycafe.assignment.io.provider.LockerPassFileReader;
import cleancode.studycafe.assignment.io.provider.SeatPassFileReader;

public class StudyCafeApplication {

    public static void main(String[] args) {
        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(
            new IOHandler(),
            new SeatPassFileReader(),
            new LockerPassFileReader()
        );

        studyCafePassMachine.initializable();
        studyCafePassMachine.run();
    }

}
