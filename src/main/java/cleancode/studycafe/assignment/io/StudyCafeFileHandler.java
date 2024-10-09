package cleancode.studycafe.assignment.io;

import cleancode.studycafe.assignment.model.StudyCafeLockerPass;
import cleancode.studycafe.assignment.model.StudyCafePass;
import cleancode.studycafe.assignment.model.StudyCafePassType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudyCafeFileHandler implements FileHandler {

    @Override
    public Map<String, List<StudyCafePass>> readStudyCafePasses() {
        try {
            Map<String, List<StudyCafePass>> studyCafePassesMap = new HashMap<>();

            List<String> lines = Files.readAllLines(Paths.get(PASS_PATH));
            for (String line : lines) {
                List<StudyCafePass> studyCafePasses = new ArrayList<>();
                String[] values = line.split(REGEX);
                StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
                int duration = Integer.parseInt(values[1]);
                int price = Integer.parseInt(values[2]);
                double discountRate = Double.parseDouble(values[3]);
                StudyCafePass studyCafePass = StudyCafePass.of(studyCafePassType, duration, price, discountRate);
                if (studyCafePassesMap.containsKey(studyCafePassType.name())) {
                    studyCafePasses = studyCafePassesMap.get(studyCafePassType.name());
                } else {
                    studyCafePassesMap.put(studyCafePassType.name(), studyCafePasses);
                }
                studyCafePasses.add(studyCafePass);
            }

            return studyCafePassesMap;
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }

    @Override
    public Map<String, List<StudyCafeLockerPass>> readLockerPasses() {
        try {
            Map<String, List<StudyCafeLockerPass>> lockerPassesMap = new HashMap<>();
            List<StudyCafeLockerPass> lockerPasses = new ArrayList<>();

            List<String> lines = Files.readAllLines(Paths.get(LOCKER_PATH));
            for (String line : lines) {
                String[] values = line.split(REGEX);
                StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
                int duration = Integer.parseInt(values[1]);
                int price = Integer.parseInt(values[2]);
                StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(studyCafePassType, duration, price);

                if (lockerPassesMap.containsKey(studyCafePassType.name())) {
                    lockerPasses = lockerPassesMap.get(studyCafePassType.name());
                } else {
                    lockerPassesMap.put(studyCafePassType.name(), lockerPasses);
                }
                lockerPasses.add(lockerPass);
            }

            return lockerPassesMap;
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }

}
