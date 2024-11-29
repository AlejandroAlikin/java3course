package ru.vsu.cs.alikin.repositories;

import ru.vsu.cs.alikin.ConnectJDBC;
import ru.vsu.cs.alikin.objects.University;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UniversityRepository {
    private static UniversityRepository instance;
    public static final String UNIVERSITY_TABLE = "university";

    public static final String UNIVERSITY_ID = "university_id";
    public static final String UNIVERSITY_NAME = "name";
    public static final String UNIVERSITY_CITY = "city";
    public static final String UNIVERSITY_ADDRESS = "address";
    public static final String UNIVERSITY_YEAR_OF_CREATION = "year_of_creation";

    public University getById(University get) throws SQLException, ClassNotFoundException {
        int realID = get.getId();
        String ins = "SELECT * FROM " + UNIVERSITY_TABLE + " WHERE " + UNIVERSITY_ID + "=?";
        ConnectJDBC con = ConnectJDBC.getInstance();

        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            prSt.setString(1, String.valueOf(realID));
            ResultSet res = prSt.executeQuery();
            res.next();
            University university = new University();
            university.setId(res.getInt(1));
            university.setName(res.getString(2));
            university.setCity(res.getString(3));
            university.setAddress(res.getString(4));
            university.setYearOfCreation(res.getDate(5));

            return university;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<University> getAll() {

        String ins = "SELECT * FROM " + UNIVERSITY_TABLE;
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            ResultSet res = prSt.executeQuery();
            List<University> universities = new ArrayList<>();
            while (res.next()) {
                University university = new University();
                university.setId(res.getInt(1));
                university.setName(res.getString(2));
                university.setCity(res.getString(3));
                university.setAddress(res.getString(4));
                university.setYearOfCreation(res.getDate(5));
                universities.add(university);
            }
            return universities;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public University create(University add) throws ClassNotFoundException {
        University agr = add;
        String ins = "INSERT INTO " + UNIVERSITY_TABLE + "(" + UNIVERSITY_ID + "," + UNIVERSITY_NAME + "," + UNIVERSITY_CITY + "," + UNIVERSITY_ADDRESS + "," + UNIVERSITY_YEAR_OF_CREATION + ")" + "VALUES(?,?,?,?,?)";
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            prSt.setInt(1, agr.getId());
            prSt.setString(2, agr.getName());
            prSt.setString(3, agr.getCity());
            prSt.setString(4, agr.getAddress());
            prSt.setDate(5, agr.getYearOfCreation());
            prSt.executeUpdate();
            return agr;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public University update(University upd) throws ClassNotFoundException, SQLException {
        University agr = upd;
        if (getById(agr) != null) {
            ConnectJDBC con = ConnectJDBC.getInstance();
            String ins = "UPDATE " + UNIVERSITY_TABLE + " SET " + UNIVERSITY_ID + " = ?, " + UNIVERSITY_NAME + " =?, " + UNIVERSITY_CITY + " =?, " + UNIVERSITY_ADDRESS + " =?, " + UNIVERSITY_YEAR_OF_CREATION + " =? WHERE " + UNIVERSITY_ID + "=" + agr.getId();

            try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
                prSt.setString(1, String.valueOf(agr.getId()));
                prSt.setString(2, agr.getName());
                prSt.setString(3, agr.getCity());
                prSt.setString(4, agr.getAddress());
                prSt.setDate(5, agr.getYearOfCreation());
                prSt.executeUpdate();
                return agr;
            } catch (SQLException | ClassNotFoundException e) {
                return null;
            }
        } else create(upd);
        return null;
    }

    public boolean delete(University del) throws SQLException, ClassNotFoundException {
        int realID = del.getId();
        if (getById(del) != null) {
            ConnectJDBC con = ConnectJDBC.getInstance();
            String ins = "DELETE FROM " + UNIVERSITY_TABLE + " WHERE " + UNIVERSITY_ID + "=?";
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

    public static synchronized UniversityRepository getInstance() {
        if (instance == null) instance = new UniversityRepository();
        return instance;
    }
}
