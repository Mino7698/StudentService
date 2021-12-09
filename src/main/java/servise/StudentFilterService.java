package servise;

import model.Student;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentFilterService {
    private final StudentParsingService studentParsingService;

    public StudentFilterService(StudentParsingService studentParsingService) {
        this.studentParsingService = studentParsingService;
    }

    public List<Student> getAllStudentsReverseSortedByName() {
        return studentParsingService.getAllStudent().stream()
                .distinct().sorted(Student::compareByStudentName).collect(Collectors.toList());
    }

    public Boolean doAllStudentsHavePositiveMarks() {
        return studentParsingService.getAllStudent().stream().map(mark -> mark.getMark())
                .allMatch(mark -> mark > 2);
    }

    public List<String> getNameOfStudentsUnderTheAgeOf21Years() {
        LocalDate currentDate = LocalDate.now();
        return studentParsingService.getAllStudent().stream()
                .filter(student -> student.getDataBirthday().isAfter(currentDate.minusYears(21)))
                .map(Student::getUserName)
                .collect(Collectors.toList());
    }

    public Map<Integer, Double> getAverageGradeInTheClass() {
        Map<Integer, Double> dataMapOut = new HashMap<>();
        studentParsingService.getAllStudent().stream()
                .collect(Collectors.groupingBy(Student::getClassId)).entrySet().stream().forEach(entry -> {
            Integer key = entry.getKey();
            double averageValue = entry.getValue().stream()
                    .mapToInt(Student::getMark)
                    .summaryStatistics().getAverage();
            dataMapOut.put(key, averageValue);
        });
        return dataMapOut;
    }

    public Map<Integer, Double> getAverageGradeInTheClass2() {

        Map<Integer, Double> dataMapOut = new HashMap<Integer, Double>();
        Map<Integer, List<Student>> mapClassIdAndStudentList = studentParsingService.getAllStudent().stream()
                .collect(Collectors.groupingBy(Student::getClassId));

        System.out.println(mapClassIdAndStudentList);

        Map<Integer, Integer> mapi = new HashMap<>();
        mapClassIdAndStudentList.entrySet().stream().forEach(entry -> {
            Integer key = entry.getKey();
            double averageValue = entry.getValue().stream()
                    .mapToInt(value -> value.getMark())
                    .summaryStatistics().getAverage();
            dataMapOut.put(key, averageValue);
        });

        return dataMapOut;
    }

}
