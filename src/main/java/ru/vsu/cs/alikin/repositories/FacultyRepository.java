package ru.vsu.cs.alikin.repositories;

import ru.vsu.cs.alikin.ConnectJDBC;
import ru.vsu.cs.alikin.objects.Faculty;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacultyRepository {
    private static FacultyRepository instance;
    public static final String FACULTY_TABLE = "faculty";

    public static final String FACULTY_ID = "faculty_id";
    public static final String FACULTY_NAME = "name";
    public static final String FACULTY_YEAR_OF_CREATION = "year_of_creation";
    public static final String FACULTY_UNIVERSITY_ID = "university_id";

    public Faculty getById(Faculty get) throws SQLException, ClassNotFoundException {
        int realID = get.getId();
        String ins = "SELECT * FROM" + FACULTY_TABLE + " WHERE " + FACULTY_ID + "=?";
        ConnectJDBC con = ConnectJDBC.getInstance();

        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            prSt.setString(1, String.valueOf(realID));
            ResultSet res = prSt.executeQuery();
            res.next();
            Faculty faculty = new Faculty();
            faculty.setId(res.getInt(1));
            faculty.setName(res.getString(2));
            faculty.setYearOfCreation(res.getInt(3));
            faculty.setUniversityId(res.getInt(4));

            return faculty;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Faculty> getAll() {

        String ins = "SELECT * FROM " + FACULTY_TABLE;
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            ResultSet res = prSt.executeQuery();
            List<Faculty> faculties = new ArrayList<>();
            while (res.next()) {
                Faculty faculty = new Faculty();
                faculty.setId(res.getInt(1));
                faculty.setName(res.getString(2));
                faculty.setYearOfCreation(res.getInt(3));
                faculty.setUniversityId(res.getInt(4));
                faculties.add(faculty);
            }
            return faculties;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Faculty create(Faculty add) throws ClassNotFoundException {
        Faculty agr = add;
        String ins = "INSERT INTO " + FACULTY_TABLE + "(" + FACULTY_ID + "," + FACULTY_NAME + "," + FACULTY_YEAR_OF_CREATION + "," + FACULTY_UNIVERSITY_ID + "," + ")" + "VALUES(?,?,?,?)";
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            prSt.setInt(1, agr.getId());
            prSt.setString(2, agr.getName());
            prSt.setInt(3, agr.getYearOfCreation());
            prSt.setInt(4, agr.getUniversityId());
            prSt.executeUpdate();
            return agr;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Faculty update(Faculty upd) throws ClassNotFoundException, SQLException {
        Faculty agr = upd;
        if (getById(agr) != null) {
            ConnectJDBC con = ConnectJDBC.getInstance();
            String ins = "UPDATE " + FACULTY_TABLE + " SET " + FACULTY_ID + " = ?, " + FACULTY_NAME + " =?, " + FACULTY_YEAR_OF_CREATION + " =?, " + FACULTY_UNIVERSITY_ID + " =? WHERE " + FACULTY_ID + "=" + agr.getId();

            try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
                prSt.setString(1, String.valueOf(agr.getId()));
                prSt.setString(2, agr.getName());
                prSt.setInt(3, agr.getYearOfCreation());
                prSt.setInt(4, agr.getUniversityId());
                prSt.executeUpdate();
                return agr;
            } catch (SQLException | ClassNotFoundException e) {
                return null;
            }
        } else create(upd);
        return null;
    }

    public boolean delete(Faculty del) throws SQLException, ClassNotFoundException {
        int realID = del.getId();
        if (getById(del) != null) {
            ConnectJDBC con = ConnectJDBC.getInstance();
            String ins = "DELETE FROM " + FACULTY_TABLE + " WHERE " + FACULTY_ID + "=?";
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

    public static synchronized FacultyRepository getInstance() {
        if (instance == null) instance = new FacultyRepository();
        return instance;
    }
}
