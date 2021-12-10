# StudentService
java API сервис:
1) StudentService - сервис для получения списка учеников (.getAllStudent) с сайта https://webhook.site/673de1bb-1f24-4e8e-9255-1c65c9a5bd18 
2) реализован метод получения всех комнат, всех комнат свободных на определенную дату (метод возвращает ArrayList модельных объектов Apartment)
     а) getNameOfStudentsUnderTheAgeOf21Years - возвращает List<String>, список c именами всех учеников, которые младше 21 года;
     б) getAverageGradeInTheClass - возвращает Map<Integer, Double>, справочник где ключ - id класса, а значением будет средняя оценка учеников в этом классе.
     в) getAllStudentsReverseSortedByName - возвращает List<Student>, список уникальных учеников отсортированных по имени пользователя от большего к меньшему
     г) StudentFilterService - сервисы фильтрации учеников, полученных с помощью предыдущего сервиса, (написаны с использованием stream api) 
    

На реализацию написаны unit-тесты с использованием Mockito.
Проект собирается с помощью maven.
