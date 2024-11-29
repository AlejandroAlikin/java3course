package ru.vsu.cs.alikin;

import ru.vsu.cs.alikin.objects.Student;

import java.util.Date;
import java.util.List;

public class Service {
    public static void main(String[] args) {
        Controller controller = new Controller();
        var univ  =  controller.addUniversity("VSU", "Voronezh", "Ун. площадь д.1", new Date());
        var faculty = controller.addFaculty("Computer Science", 1998, univ.getId());
        var direction = controller.addDirection("Программная инженерия", "Бакалавриат", faculty.getId());
//        var direction2 = controller.addDirection("инженерия", "Бакалавриат", faculty.getId());
//        var student = controller.addStudent("Alexander", "Alikin", "Alexandrovich", new Date(), direction.getId(), univ.getId());
//        var student2 = controller.addStudent("Alexander", "Alikin", "Alexandrovich", new Date(), direction2.getId(), univ.getId());

        List<Student> list = controller.getAllStudents();
        for (Student stud : list) {
            System.out.println(stud.toString());
        }

        System.out.println(controller.removeUniversityById(0));

        List<Student> list2 = controller.getAllStudents();
        for (Student stud : list2) {
            System.out.println(stud.toString());
        }
    }
}
