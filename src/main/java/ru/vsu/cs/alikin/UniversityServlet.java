package ru.vsu.cs.alikin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.vsu.cs.alikin.objects.University;
import ru.vsu.cs.alikin.services.UniversityService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "universityServlet", value = "/university-servlet")
public class UniversityServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("/University.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ServletException {
        req.setCharacterEncoding("UTF-8");
        if(req.getParameter("get") != null) {
            try {
                University university = new University(Integer.parseInt(req.getParameter("id")), " ", " ", " ", new java.sql.Date(System.currentTimeMillis()));
                University agr = UniversityService.get(university);
                req.setAttribute("id", agr.getId());
                req.setAttribute("name", agr.getName());
                req.setAttribute("city", agr.getCity());
                req.setAttribute("address", agr.getAddress());
                req.setAttribute("year_of_creation", agr.getYearOfCreation());
                req.setAttribute("successMessage", "Университет был получен!");
            } catch (Exception e){
                e.printStackTrace();
                req.setAttribute("errorMessage", "Задан пустой ID или вне диапазона!");
            }
        } else if (req.getParameter("create") != null) {
            University university = createUniversity(req);
            try {
                if(UniversityService.create(university)) req.setAttribute("successMessage", "Университет успешно создан!");
                else req.setAttribute("errorMessage", "Университет не был создан!");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("update") != null) {
            University university = createUniversity(req);
            try {
                if (UniversityService.update(university)!=null) req.setAttribute("successMessage", "Успешно обновлено!");
                else req.setAttribute("errorMessage","Университет не был обновлен!");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("delete") != null) {
            //нужно подтверждение
            University university = new University(Integer.parseInt(req.getParameter("id")), " ", " ", " ", new java.sql.Date(System.currentTimeMillis()));
            try {
                if (UniversityService.delete(university)) req.setAttribute("successMessage", "Успешно удалено!");
                else req.setAttribute("errorMessage","Университет не был удален!");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("getAll") != null) {
            req.setAttribute("universities", UniversityService.getAll());
        }

        req.getRequestDispatcher("/University.jsp").forward(req, resp);
    }

    private University createUniversity(HttpServletRequest req) {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String city = req.getParameter("city");
        String address = req.getParameter("address");
        String yearOfCreation = req.getParameter("year_of_creation");

        // Проверяем, если дата пустая, то устанавливаем дефолтное значение
        java.sql.Date sqlDate = null;
        if (yearOfCreation != null && !yearOfCreation.trim().isEmpty()) {
            try {
                // Парсим строку с датой и преобразуем в java.sql.Date
                java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(yearOfCreation);
                sqlDate = new java.sql.Date(utilDate.getTime());
            } catch (ParseException e) {
                throw new RuntimeException("Ошибка при преобразовании даты: " + yearOfCreation, e);
            }
        } else {
            // Если дата пустая, то устанавливаем дефолтную дату (например, текущую дату)
            sqlDate = new java.sql.Date(System.currentTimeMillis());
        }

        try {
            // Создаем объект студента
            University university = new University(
                    Integer.parseInt(id),
                    name,
                    city,
                    address,
                    sqlDate
            );
            return university;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new University(0, " ", " ", " ", new java.sql.Date(System.currentTimeMillis())); // default sqlDate
        }
    }

    public void destroy() {
    }
}
