package servise;

import model.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentFilterService {
    private final StudentService studentParsingService;

    public StudentFilterService(StudentService studentParsingService) {
        this.studentParsingService = studentParsingService;
    }

    public List<Student> getAllStudentsReverseSortedByName() {
        return studentParsingService.getAllStudent().stream()
                .distinct().sorted(Student::compareByStudentName).collect(Collectors.toList());
    }

    public Boolean doAllStudentsHavePositiveMarks() {
        return studentParsingService.getAllStudent().stream().map(Student::getMark)
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
        return studentParsingService.getAllStudent().stream()
                .collect(Collectors.groupingBy(Student::getClassId)).entrySet()
                .stream().collect(Collectors.toMap(
                        Map.Entry::getKey, entry -> entry.getValue().stream()
                                .mapToInt(Student::getMark)
                                .summaryStatistics().getAverage()));
    }


}
