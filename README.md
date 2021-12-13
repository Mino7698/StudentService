# StudentService
java API сервис:
1) StudentService - сервис для получения списка учеников (.getAllStudent) с сайта https://webhook.site/673de1bb-1f24-4e8e-9255-1c65c9a5bd18 
2) StudentFilterService  - сервис фильтрации учеников, полученных с помощью предыдущего сервиса (реализован с использованием stream api)
3) getNameOfStudentsUnderTheAgeOf21Years - возвращает List<String>, список c именами всех учеников, которые младше 21 года   
4) getNameOfStudentsUnderTheAgeOf21Years - возвращает List<String>, список c именами всех учеников, которые младше 21 года   
5) tAverageGradeInTheClass - возвращает Map<Integer, Double>, справочник где ключ - id класса, а значением будет средняя оценка учеников в этом классе.
6) getAllStudentsReverseSortedByName - возвращает List<Student>, список уникальных учеников отсортированных по имени пользователя от большего к меньшему 
    

На реализацию написаны unit-тесты с использованием Mockito.
Проект собирается с помощью maven.
