package ru.vsu.cs.alikin.repositories;

import ru.vsu.cs.alikin.ConnectJDBC;
import ru.vsu.cs.alikin.objects.Subject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectRepository {
    private static SubjectRepository instance;
    public static final String SUBJECT_TABLE = "subject";

    public static final String SUBJECT_ID = "subject_id";
    public static final String SUBJECT_NAME = "name";

    public Subject getById(Subject get) throws SQLException, ClassNotFoundException {
        int realID = get.getId();
        String ins = "SELECT * FROM " + SUBJECT_TABLE + " WHERE " + SUBJECT_ID + "=?";
        ConnectJDBC con = ConnectJDBC.getInstance();

        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            prSt.setString(1, String.valueOf(realID));
            ResultSet res = prSt.executeQuery();
            res.next();
            Subject subject = new Subject();
            subject.setId(res.getInt(1));
            subject.setName(res.getString(2));

            return subject;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Subject> getAll() {

        String ins = "SELECT * FROM " + SUBJECT_TABLE;
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            ResultSet res = prSt.executeQuery();
            List<Subject> subjects = new ArrayList<>();
            while (res.next()) {
                Subject subject = new Subject();
                subject.setId(res.getInt(1));
                subject.setName(res.getString(2));
                subjects.add(subject);
            }
            return subjects;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Subject create(Subject add) throws ClassNotFoundException {
        Subject agr = add;
        String ins = "INSERT INTO " + SUBJECT_TABLE + "(" + SUBJECT_ID + "," + SUBJECT_NAME + ")" + "VALUES(?,?)";
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            prSt.setInt(1, agr.getId());
            prSt.setString(2, agr.getName());
            prSt.executeUpdate();
            return agr;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Subject update(Subject upd) throws ClassNotFoundException, SQLException {
        Subject agr = upd;
        if (getById(agr) != null) {
            ConnectJDBC con = ConnectJDBC.getInstance();
            String ins = "UPDATE " + SUBJECT_TABLE + " SET " + SUBJECT_ID + " = ?, " + SUBJECT_NAME + " =? WHERE " + SUBJECT_ID + "=" + agr.getId();

            try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
                prSt.setString(1, String.valueOf(agr.getId()));
                prSt.setString(2, agr.getName());
                prSt.executeUpdate();
                return agr;
            } catch (SQLException | ClassNotFoundException e) {
                return null;
            }
        } else create(upd);
        return null;
    }

    public boolean delete(Subject del) throws SQLException, ClassNotFoundException {
        int realID = del.getId();
        if (getById(del) != null) {
            ConnectJDBC con = ConnectJDBC.getInstance();
            String ins = "DELETE FROM " + SUBJECT_TABLE + " WHERE " + SUBJECT_ID + "=?";
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

    public static synchronized SubjectRepository getInstance() {
        if (instance == null) instance = new SubjectRepository();
        return instance;
    }
}
