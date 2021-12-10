package servise;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Student;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;



public class StudentService {
    public List<Student> getAllStudent() {

        try {
            final Content getResult;
            getResult = Request.Get("https://webhook.site/673de1bb-1f24-4e8e-9255-1c65c9a5bd18")
                    .execute().returnContent();
            Map jsonRead = new ObjectMapper().readValue(getResult.asString(), Map.class);
            List<Map<String,Object>> notParsedStudents = (List<Map<String, Object>>) jsonRead.get("students");
            return notParsedStudents.stream()
                    .map(student -> new Student((Integer) student.get("userId"),
                                        (String) student.get("userName"),
                                        (String) student.get("dataBirthday"),
                                        (Integer) student.get("classId"),
                                        (Integer) student.get("mark")))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return Collections.emptyList();
        }
}
}
