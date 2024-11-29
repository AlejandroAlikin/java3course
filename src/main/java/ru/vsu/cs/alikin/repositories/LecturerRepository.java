package ru.vsu.cs.alikin.repositories;

import ru.vsu.cs.alikin.ConnectJDBC;
import ru.vsu.cs.alikin.objects.Lecturer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LecturerRepository {
    private static LecturerRepository instance;
    public static final String LECTURER_TABLE = "lecturer";

    public static final String LECTURER_ID = "lecturer_id";
    public static final String LECTURER_NAME = "name";
    public static final String LECTURER_SURNAME = "surname";
    public static final String LECTURER_PATRONYMIC = "patronymic";
    public static final String LECTURER_POST_ID = "post_id";

    public Lecturer getById(Lecturer get) throws SQLException, ClassNotFoundException {
        int realID = get.getId();
        String ins = "SELECT * FROM" + LECTURER_TABLE + " WHERE " + LECTURER_ID + "=?";
        ConnectJDBC con = ConnectJDBC.getInstance();

        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            prSt.setString(1, String.valueOf(realID));
            ResultSet res = prSt.executeQuery();
            res.next();
            Lecturer lecturer = new Lecturer();
            lecturer.setId(res.getInt(1));
            lecturer.setName(res.getString(2));
            lecturer.setSurname(res.getString(3));
            lecturer.setPatronymic(res.getString(4));
            lecturer.setPostId(res.getInt(5));

            return lecturer;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Lecturer> getAll() {

        String ins = "SELECT * FROM " + LECTURER_TABLE;
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            ResultSet res = prSt.executeQuery();
            List<Lecturer> lecturers = new ArrayList<>();
            while (res.next()) {
                Lecturer lecturer = new Lecturer();
                lecturer.setId(res.getInt(1));
                lecturer.setName(res.getString(2));
                lecturer.setSurname(res.getString(3));
                lecturer.setPatronymic(res.getString(4));
                lecturer.setPostId(res.getInt(5));
                lecturers.add(lecturer);
            }
            return lecturers;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Lecturer create(Lecturer add) throws ClassNotFoundException {
        Lecturer agr = add;
        String ins = "INSERT INTO " + LECTURER_TABLE + "(" + LECTURER_ID + "," + LECTURER_NAME + "," + LECTURER_SURNAME + "," + LECTURER_PATRONYMIC + "," + LECTURER_POST_ID + ")" + "VALUES(?,?,?,?,?)";
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            prSt.setInt(1, agr.getId());
            prSt.setString(2, agr.getName());
            prSt.setString(3, agr.getSurname());
            prSt.setString(4, agr.getPatronymic());
            prSt.setInt(5, agr.getPostId());
            prSt.executeUpdate();
            return agr;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Lecturer update(Lecturer upd) throws ClassNotFoundException, SQLException {
        Lecturer agr = upd;
        if (getById(agr) != null) {
            ConnectJDBC con = ConnectJDBC.getInstance();
            String ins = "UPDATE " + LECTURER_TABLE + " SET " + LECTURER_ID + " = ?, " + LECTURER_NAME + " =?, " + LECTURER_SURNAME + " =?, " + LECTURER_PATRONYMIC + " =?, " + LECTURER_POST_ID + " =? WHERE " + LECTURER_ID + "=" + agr.getId();

            try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
                prSt.setString(1, String.valueOf(agr.getId()));
                prSt.setString(2, agr.getName());
                prSt.setString(3, agr.getSurname());
                prSt.setString(4, agr.getPatronymic());
                prSt.setInt(5, agr.getPostId());
                prSt.executeUpdate();
                return agr;
            } catch (SQLException | ClassNotFoundException e) {
                return null;
            }
        } else create(upd);
        return null;
    }

    public boolean delete(Lecturer del) throws SQLException, ClassNotFoundException {
        int realID = del.getId();
        if (getById(del) != null) {
            ConnectJDBC con = ConnectJDBC.getInstance();
            String ins = "DELETE FROM " + LECTURER_TABLE + " WHERE " + LECTURER_ID + "=?";
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

    public static synchronized LecturerRepository getInstance() {
        if (instance == null) instance = new LecturerRepository();
        return instance;
    }
}
