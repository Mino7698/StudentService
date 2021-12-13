package model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@EqualsAndHashCode
@Getter
@Builder
@ToString

public class Student {
    private final int userId;
    private final String userName;
    private final LocalDate dataBirthday;
    private final int classId;
    private final int mark;

    public Student(int userId, String userName, String dataBirthday, int classId, int mark) {
        this.userId = userId;
        this.userName = userName;
        this.dataBirthday = LocalDate.of(Integer.parseInt(dataBirthday.substring(6)),
                Integer.parseInt(dataBirthday.substring(3, 5)),
                Integer.parseInt(dataBirthday.substring(0, 2)));
        this.classId = classId;
        this.mark = mark;
    }

    // used for builder in testing
    public Student(int userId, String userName, LocalDate dataBirthday, int classId, int mark) {
        this.userId = userId;
        this.userName = userName;
        this.dataBirthday = dataBirthday;
        this.classId = classId;
        this.mark = mark;
    }

    public int compareByStudentName(Student student) {
        return -this.getUserName().toUpperCase().compareTo(student.getUserName().toUpperCase());
    }

}
