package ru.vsu.cs.alikin;

import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.vsu.cs.alikin.objects.Student;
import ru.vsu.cs.alikin.services.StudentService;

@WebServlet(name = "studentServlet", value = "/student-servlet")
public class StudentServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("/Student.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ServletException {
        req.setCharacterEncoding("UTF-8");
        if(req.getParameter("get") != null) {
            try {
                Student student = new Student(Integer.parseInt(req.getParameter("id")), " ", " ", " ", new java.sql.Date(System.currentTimeMillis()), 0);
                Student agr = StudentService.get(student);
                req.setAttribute("id", agr.getId());
                req.setAttribute("name", agr.getName());
                req.setAttribute("surname", agr.getSurname());
                req.setAttribute("patronymic", agr.getPatronymic());
                req.setAttribute("birthday", agr.getBirthDay());
                req.setAttribute("direction_id", agr.getDirectionId());
                req.setAttribute("successMessage", "Студент был получен!");
            } catch (Exception e){
                e.printStackTrace();
                req.setAttribute("errorMessage", "Задан пустой ID или вне диапазона!");
            }
        } else if (req.getParameter("create") != null) {
            Student student = createStudent(req);
            try {
                if(StudentService.create(student)) req.setAttribute("successMessage", "Студент успешно создан!");
                else req.setAttribute("errorMessage", "Cтудент не был создан!");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("update") != null) {
            Student student = createStudent(req);
            try {
                if (StudentService.update(student)!=null) req.setAttribute("successMessage", "Успешно обновлено!");
                else req.setAttribute("errorMessage","Студент не был обновлен!");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("delete") != null) {
            //нужно подтверждение
            Student student = new Student(Integer.parseInt(req.getParameter("id")), " ", " ", " ", new java.sql.Date(System.currentTimeMillis()), 0);
            try {
                if (StudentService.delete(student)) req.setAttribute("successMessage", "Успешно удалено!");
                else req.setAttribute("errorMessage","Студент не был удален!");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("getAll") != null) {
            req.setAttribute("students", StudentService.getAll());
        }

        req.getRequestDispatcher("/Student.jsp").forward(req, resp);
    }

    private Student createStudent(HttpServletRequest req) {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String patronymic = req.getParameter("patronymic");
        String birthday = req.getParameter("birthday");
        String directionId = req.getParameter("directionId");

        // Проверяем, если дата пустая, то устанавливаем дефолтное значение
        java.sql.Date sqlDate = null;
        if (birthday != null && !birthday.trim().isEmpty()) {
            try {
                // Парсим строку с датой и преобразуем в java.sql.Date
                java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
                sqlDate = new java.sql.Date(utilDate.getTime());
            } catch (ParseException e) {
                throw new RuntimeException("Ошибка при преобразовании даты: " + birthday, e);
            }
        } else {
            // Если дата пустая, то устанавливаем дефолтную дату (например, текущую дату)
            sqlDate = new java.sql.Date(System.currentTimeMillis());
        }

        try {
            // Создаем объект студента
            Student student = new Student(
                    Integer.parseInt(id),
                    name,
                    surname,
                    patronymic,
                    sqlDate, // Используем java.sql.Date
                    Integer.parseInt(directionId)
            );
            return student;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new Student(0, " ", " ", " ", new java.sql.Date(System.currentTimeMillis()), 0); // default sqlDate
        }
    }

    public void destroy() {
    }
}