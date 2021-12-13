import model.Student;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import servise.StudentService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {
    private final StudentService studentServiceForTest = new StudentService();
    @Rule
    public ExpectedException exception = ExpectedException.none();

    String stringForMockTestContentAsString;
    List<Student> studentListForTests = new ArrayList<>();

    @Mock
    Request mockTestRequest;
    @Mock
    Response mockTestResponse;
    @Mock
    Content mockTestContent;

    public void createStringForMockTestContentAsString() {
        stringForMockTestContentAsString = """
                {
                  "students": [
                    {
                      "userId": 1,
                      "userName": "Petrosyan Eugeny",
                      "dataBirthday": "16.09.1945",
                      "classId": 1,
                      "mark": 5
                    },
                    {
                      "userId": 2,
                      "userName": "Capitan HenryMorgan",
                      "dataBirthday": "24.01.1635",
                      "classId": 1,
                      "mark": 4
                    },
                    {
                      "userId": 3,
                      "userName": "Confucius",
                      "dataBirthday": "05.05.-497",
                      "classId": 1,
                      "mark": 5
                    },   \s
                    {
                      "userId": 4,
                      "userName": "Claudius Ptolemy",
                      "dataBirthday": "28.02.177",
                      "classId": 1,
                      "mark": 4
                    },

                    {
                      "userId": 5,
                      "userName": "Dzu Kostya",
                      "dataBirthday": "28.07.1999",
                      "classId": 2,
                      "mark": 2
                    },   \s
                    {
                      "userId": 6,
                      "userName": "Petr Emelianenko",
                      "dataBirthday": "05.06.2001",
                      "classId": 2,
                      "mark": 2
                    },   \s
                    {
                      "userId": 7,
                      "userName": "Santa Barbara",
                      "dataBirthday": "13.08.2002",
                      "classId": 2,
                      "mark": 2
                    },

                    {
                      "userId": 8,
                      "userName": "Grozny Ivan",
                      "dataBirthday": "31.12.2003",
                      "classId": 3,
                      "mark": 1
                    },   \s
                    {
                      "userId": 9,
                      "userName": "Petr The First",
                      "dataBirthday": "20.12.2001",
                      "classId": 3,
                      "mark": 1
                    },   \s
                    {
                      "userId": 10,
                      "userName": "Vladimir The Red Sun",
                      "dataBirthday": "31.01.2003",
                      "classId": 3,
                      "mark": 1
                    },

                    {
                      "userId": 11,
                      "userName": "Birckgun Sergey",
                      "dataBirthday": "28.08.1950",
                      "classId": 4,
                      "mark": 5
                    },   \s
                    {
                      "userId": 12,
                      "userName": "Gladkov Semen",
                      "dataBirthday": "02.06.1998",
                      "classId": 4,
                      "mark": 3
                    },   \s
                    {
                      "userId": 13,
                      "userName": "Sharonova Alena",
                      "dataBirthday": "15.05.1992",
                      "classId": 4,
                      "mark": 4
                    }
                  ]
                }""";
    }

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

        this.studentListForTests = studentList;
    }

    @Before
    public void setUp() {
        creatingListOfStudentsForTests();
        createStringForMockTestContentAsString();
    }

    @Test
    public void studentServiceTestWithMockingRequestGet() {
        Request myTestRequest = Request.Get("https://vk.com/doc68066890_619731388");
        try (MockedStatic<Request> mockedRequest = Mockito.mockStatic(Request.class)) {
            mockedRequest.when(() -> Request.Get("https://webhook.site/673de1bb-1f24-4e8e-9255-1c65c9a5bd18"))
                    .thenReturn(myTestRequest);
            Assert.assertEquals(studentServiceForTest.getAllStudent(), studentListForTests);

            mockedRequest.verify(() -> Request.Get("https://webhook.site/673de1bb-1f24-4e8e-9255-1c65c9a5bd18"));
        }
    }

    @Test
    public void studentServiceTestWithMockingAllTrace() {
        try (MockedStatic<Request> mockedRequest = Mockito.mockStatic(Request.class)) {
            mockedRequest.when(() -> Request.Get("https://webhook.site/673de1bb-1f24-4e8e-9255-1c65c9a5bd18"))
                    .thenReturn(mockTestRequest);
            Mockito.when(mockTestContent.asString()).thenReturn(stringForMockTestContentAsString);
            Mockito.when(mockTestResponse.returnContent()).thenReturn(mockTestContent);
            Mockito.when(mockTestRequest.execute()).thenReturn(mockTestResponse);

            Assert.assertEquals(studentServiceForTest.getAllStudent(), studentListForTests);

            mockedRequest.verify(() -> Request.Get("https://webhook.site/673de1bb-1f24-4e8e-9255-1c65c9a5bd18"));
            Mockito.verify(mockTestContent).asString();
            Mockito.verifyNoMoreInteractions(mockTestContent);
            Mockito.verify(mockTestResponse).returnContent();
            Mockito.verifyNoMoreInteractions(mockTestResponse);
            Mockito.verify(mockTestRequest).execute();
            Mockito.verifyNoMoreInteractions(mockTestRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
