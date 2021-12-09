package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;

@EqualsAndHashCode
@Getter

public class Student {
    private int userId;
    private String userName;
    private LocalDate dataBirthday;
    private int classId;
    private int mark;

    public Student(){
    }

    public Student(int userId, String userName, String dataBirthday, int classId, int mark) {
        this.userId = userId;
        this.userName = userName;
        this.dataBirthday = LocalDate.of(Integer.parseInt(dataBirthday.substring(6,10)),
                Integer.parseInt(dataBirthday.substring(3,5)),
                Integer.parseInt(dataBirthday.substring(0,2)));
        this.classId = classId;
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Student{" +
                "userId1111=" + userId +
                ", userName='" + userName + '\'' +
                ", dataBirthday='" + dataBirthday + '\'' +
                ", classId=" + classId +
                ", mark=" + mark +
                '}'+'\n';
    }

    public int compareByStudentName(Student student) {
        return -this.getUserName().toUpperCase().compareTo(student.getUserName().toUpperCase());
    }

    public int compareByClassId(Student student) {
        return this.getClassId()-student.classId;
    }
}
