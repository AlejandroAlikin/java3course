package ru.vsu.cs.alikin.repositories;

import ru.vsu.cs.alikin.ConnectJDBC;
import ru.vsu.cs.alikin.objects.Direction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DirectionRepository {
    private static DirectionRepository instance;
    public static final String DIRECTION_TABLE = "direction";

    public static final String DIRECTION_ID = "direction_id";
    public static final String DIRECTION_NAME = "name";
    public static final String DIRECTION_QUALIFICATION = "qualification";
    public static final String DIRECTION_FACULTY_ID = "faculty_id";

    public Direction getById(Direction get) throws SQLException, ClassNotFoundException {
        int realID = get.getId();
        String ins = "SELECT * FROM" + DIRECTION_TABLE + " WHERE " + DIRECTION_ID + "=?";
        ConnectJDBC con = ConnectJDBC.getInstance();

        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            prSt.setString(1, String.valueOf(realID));
            ResultSet res = prSt.executeQuery();
            res.next();
            Direction direction = new Direction();
            direction.setId(res.getInt(1));
            direction.setName(res.getString(2));
            direction.setQualification(res.getString(3));
            direction.setFacultyId(res.getInt(4));

            return direction;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Direction> getAll() {

        String ins = "SELECT * FROM " + DIRECTION_TABLE;
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            ResultSet res = prSt.executeQuery();
            List<Direction> directions = new ArrayList<>();
            while (res.next()) {
                Direction direction = new Direction();
                direction.setId(res.getInt(1));
                direction.setName(res.getString(2));
                direction.setQualification(res.getString(3));
                direction.setFacultyId(res.getInt(4));
                directions.add(direction);
            }
            return directions;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Direction create(Direction add) throws ClassNotFoundException {
        Direction agr = add;
        String ins = "INSERT INTO " + DIRECTION_TABLE + "(" + DIRECTION_ID + "," + DIRECTION_NAME + "," + DIRECTION_QUALIFICATION + "," + DIRECTION_FACULTY_ID + ")" + "VALUES(?,?,?,?)";
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            prSt.setInt(1, agr.getId());
            prSt.setString(2, agr.getName());
            prSt.setString(3, agr.getQualification());
            prSt.setInt(4, agr.getFacultyId());
            prSt.executeUpdate();
            return agr;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Direction update(Direction upd) throws ClassNotFoundException, SQLException {
        Direction agr = upd;
        if (getById(agr) != null) {
            ConnectJDBC con = ConnectJDBC.getInstance();
            String ins = "UPDATE " + DIRECTION_TABLE + " SET " + DIRECTION_ID + " = ?, " + DIRECTION_NAME + " =?, " + DIRECTION_QUALIFICATION + " =?, " + DIRECTION_FACULTY_ID + " =? WHERE " + DIRECTION_ID + "=" + agr.getId();

            try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
                prSt.setString(1, String.valueOf(agr.getId()));
                prSt.setString(2, agr.getName());
                prSt.setString(3, agr.getQualification());
                prSt.setInt(4, agr.getFacultyId());
                prSt.executeUpdate();
                return agr;
            } catch (SQLException | ClassNotFoundException e) {
                return null;
            }
        } else create(upd);
        return null;
    }

    public boolean delete(Direction del) throws SQLException, ClassNotFoundException {
        int realID = del.getId();
        if (getById(del) != null) {
            ConnectJDBC con = ConnectJDBC.getInstance();
            String ins = "DELETE FROM " + DIRECTION_TABLE + " WHERE " + DIRECTION_ID + "=?";
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

    public static synchronized DirectionRepository getInstance() {
        if (instance == null) instance = new DirectionRepository();
        return instance;
    }
}