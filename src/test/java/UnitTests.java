import model.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import servise.StudentFilterService;
import servise.StudentService;

import java.time.LocalDate;
import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class UnitTests {

    @Rule
    public ExpectedException exception = ExpectedException.none();
    private StudentFilterService mockStudentFilterService;
    List<Student> studentList = new ArrayList<>();

    @Mock
    private StudentService mockStudentService;

    public void creatingListOfStudentsForTests() {
        List<Student> studentList = new ArrayList<>();

            studentList.add(Student.builder().userId(1).userName("Petrosyan Eugeny")
                    .dataBirthday(LocalDate.of(1945, 9, 16))
                    .classId(1).mark(5).build());
            studentList.add(Student.builder().userId(2).userName("Capitan HenryMorgan")
                    .dataBirthday(LocalDate.of(1635, 1, 24))
                    .classId(1).mark(4).build());
            studentList.add(Student.builder().userId(3).userName("Confucius")
                    .dataBirthday(LocalDate.of(-497, 5, 5))
                    .classId(1).mark(5).build());
            studentList.add(Student.builder().userId(4).userName("Claudius Ptolemy")
                    .dataBirthday(LocalDate.of(177, 2, 28))
                    .classId(1).mark(4).build());

            studentList.add(Student.builder().userId(5).userName("Dzu Kostya")
                    .dataBirthday(LocalDate.of(1999, 7, 28))
                    .classId(2).mark(2).build());
            studentList.add(Student.builder().userId(6).userName("Petr Emelianenko")
                    .dataBirthday(LocalDate.of(2001, 6, 5))
                    .classId(2).mark(2).build());
            studentList.add(Student.builder().userId(7).userName("Santa Barbara")
                    .dataBirthday(LocalDate.of(2002, 8, 13))
                    .classId(2).mark(2).build());

            studentList.add(Student.builder().userId(8).userName("Grozny Ivan")
                    .dataBirthday(LocalDate.of(2003, 12, 31))
                    .classId(3).mark(1).build());
            studentList.add(Student.builder().userId(9).userName("Petr The First")
                    .dataBirthday(LocalDate.of(2001, 12, 20))
                    .classId(3).mark(1).build());
            studentList.add(Student.builder().userId(10).userName("Vladimir The Red Sun")
                    .dataBirthday(LocalDate.of(2003, 1, 31))
                    .classId(3).mark(1).build());

            studentList.add(Student.builder().userId(11).userName("Birckgun Sergey")
                    .dataBirthday(LocalDate.of(1950, 8, 28))
                    .classId(4).mark(5).build());
            studentList.add(Student.builder().userId(12).userName("Gladkov Semen")
                    .dataBirthday(LocalDate.of(1998, 6, 2))
                    .classId(4).mark(3).build());
            studentList.add(Student.builder().userId(13).userName("Sharonova Alena")
                    .dataBirthday(LocalDate.of(1992, 5, 15))
                    .classId(4).mark(4).build());

        this.studentList = studentList;
    }

    @Before
    public void setUp(){
        mockStudentFilterService = new StudentFilterService(mockStudentService);
        creatingListOfStudentsForTests();
    }

    @Test
    public void getAllStudentsReverseSortedByNameTest() {
        List<Student> sortedStudentList = studentList;
        sortedStudentList.sort(UnitTests::compareByStudentName);
        Mockito.when(mockStudentService.getAllStudent()).thenReturn(studentList);

        Assert.assertEquals(sortedStudentList, mockStudentFilterService.getAllStudentsReverseSortedByName());

        Mockito.verify(mockStudentService).getAllStudent();
        Mockito.verifyNoMoreInteractions(mockStudentService);
    }

    @Test
    public void getNameOfStudentsUnderTheAgeOf21YearsTest() {
        List<String> namesOfStudentsUnderTheAgeOf21YearsList = Arrays.asList("Petr Emelianenko", "Santa Barbara", "Grozny Ivan", "Petr The First", "Vladimir The Red Sun");

        Mockito.when(mockStudentService.getAllStudent()).thenReturn(studentList);

        Assert.assertEquals(namesOfStudentsUnderTheAgeOf21YearsList, mockStudentFilterService.getNameOfStudentsUnderTheAgeOf21Years());

        Mockito.verify(mockStudentService).getAllStudent();
        Mockito.verifyNoMoreInteractions(mockStudentService);
    }

    @Test
    public void getAverageGradeInTheClassTest() {
        Map<Integer,Double> mapOfAverageGradeInTheClass = new HashMap<>();
        mapOfAverageGradeInTheClass.put(1,4.5);
        mapOfAverageGradeInTheClass.put(2,2.0);
        mapOfAverageGradeInTheClass.put(3,1.0);
        mapOfAverageGradeInTheClass.put(4,4.0);
        Mockito.when(mockStudentService.getAllStudent()).thenReturn(studentList);

        Assert.assertEquals(mapOfAverageGradeInTheClass, mockStudentFilterService.getAverageGradeInTheClass());

        Mockito.verify(mockStudentService).getAllStudent();
        Mockito.verifyNoMoreInteractions(mockStudentService);
    }


    @Test
    public void doAllStudentsHavePositiveMarksTest() {
        Mockito.when(mockStudentService.getAllStudent()).thenReturn(studentList);

        Assert.assertEquals(false, mockStudentFilterService.doAllStudentsHavePositiveMarks());

        Mockito.verify(mockStudentService).getAllStudent();
        Mockito.verifyNoMoreInteractions(mockStudentService);
    }

    public static int compareByStudentName(Student firstStudent, Student secondStudent) {
        return -firstStudent.getUserName().toUpperCase().compareTo(secondStudent.getUserName().toUpperCase());
    }


}

