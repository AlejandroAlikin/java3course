package ru.vsu.cs.alikin.repositories;

import ru.vsu.cs.alikin.ConnectJDBC;
import ru.vsu.cs.alikin.objects.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private static StudentRepository instance;
    public static final String STUDENT_TABLE = "student";

    public static final String STUDENT_ID = "student_id";
    public static final String STUDENT_NAME = "name";
    public static final String STUDENT_SURNAME = "surname";
    public static final String STUDENT_PATRONYMIC = "patronymic";
    public static final String STUDENT_BIRTHDAY = "birthday";
    public static final String STUDENT_DIRECTION_ID = "direction_id";

    public Student getById(Student get) throws SQLException, ClassNotFoundException {
        int realID = get.getId();
        String ins = "SELECT * FROM " + STUDENT_TABLE + " WHERE " + STUDENT_ID + "=?";
        ConnectJDBC con = ConnectJDBC.getInstance();

        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            prSt.setString(1, String.valueOf(realID));
            ResultSet res = prSt.executeQuery();
            res.next();
            Student student = new Student();
            student.setId(res.getInt(1));
            student.setName(res.getString(2));
            student.setSurname(res.getString(3));
            student.setPatronymic(res.getString(4));
            student.setBirthDay(res.getDate(5));
            student.setDirectionId(res.getInt(6));

            return student;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Student> getAll() {

        String ins = "SELECT * FROM " + STUDENT_TABLE;
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            ResultSet res = prSt.executeQuery();
            List<Student> students = new ArrayList<>();
            while (res.next()) {
                Student student = new Student();
                student.setId(res.getInt(1));
                student.setName(res.getString(2));
                student.setSurname(res.getString(3));
                student.setPatronymic(res.getString(4));
                student.setBirthDay(res.getDate(5));
                student.setDirectionId(res.getInt(6));
                students.add(student);
            }
            return students;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Student create(Student add) throws ClassNotFoundException {
        Student agr = add;
        String ins = "INSERT INTO " + STUDENT_TABLE + "(" + STUDENT_ID + "," + STUDENT_NAME + "," + STUDENT_SURNAME + "," + STUDENT_PATRONYMIC + "," + STUDENT_BIRTHDAY + "," + STUDENT_DIRECTION_ID + ")" + "VALUES(?,?,?,?,?,?)";
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            prSt.setInt(1, agr.getId());
            prSt.setString(2, agr.getName());
            prSt.setString(3, agr.getSurname());
            prSt.setString(4, agr.getPatronymic());
            prSt.setDate(5, agr.getBirthDay());
            prSt.setInt(6, agr.getDirectionId());
            prSt.executeUpdate();
            return agr;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Student update(Student upd) throws ClassNotFoundException, SQLException {
        Student agr = upd;
        if (getById(agr) != null) {
            ConnectJDBC con = ConnectJDBC.getInstance();
            String ins = "UPDATE " + STUDENT_TABLE + " SET " + STUDENT_ID + " = ?, " + STUDENT_NAME + " =?, " + STUDENT_SURNAME + " =?, " + STUDENT_PATRONYMIC + " =?, " + STUDENT_BIRTHDAY + " =?, " + STUDENT_DIRECTION_ID + " =?  WHERE " + STUDENT_ID + "=" + agr.getId();

            try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
                prSt.setString(1, String.valueOf(agr.getId()));
                prSt.setString(2, agr.getName());
                prSt.setString(3, agr.getSurname());
                prSt.setString(4, agr.getPatronymic());
                prSt.setDate(5, agr.getBirthDay());
                prSt.setInt(6, agr.getDirectionId());
                prSt.executeUpdate();
                return agr;
            } catch (SQLException | ClassNotFoundException e) {
                return null;
            }
        } else create(upd);
        return null;
    }

    public boolean delete(Student del) throws SQLException, ClassNotFoundException {
        int realID = del.getId();
        if (getById(del) != null) {
            ConnectJDBC con = ConnectJDBC.getInstance();
            String ins = "DELETE FROM " + STUDENT_TABLE + " WHERE " + STUDENT_ID + "=?";
            try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
                prSt.setString(1, String.valueOf(realID));
                prSt.executeUpdate();
                return true;
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else System.out.println("Nothing to delete.");
        return false;
    }

    public static synchronized StudentRepository getInstance() {
        if (instance == null) instance = new StudentRepository();
        return instance;
    }
}
