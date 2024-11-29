package ru.vsu.cs.alikin.repositories;

import ru.vsu.cs.alikin.ConnectJDBC;
import ru.vsu.cs.alikin.objects.Post;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostRepository {
    private static PostRepository instance;
    public static final String POST_TABLE = "post";

    public static final String POST_ID = "post_id";
    public static final String POST_NAME = "name";
    public static final String POST_SALARY = "salary";
    public static final String POST_UNIVERSITY_ID = "university_id";

    public Post getById(Post get) throws SQLException, ClassNotFoundException {
        int realID = get.getId();
        String ins = "SELECT * FROM " + POST_TABLE + " WHERE " + POST_ID + "=?";
        ConnectJDBC con = ConnectJDBC.getInstance();

        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            prSt.setString(1, String.valueOf(realID));
            ResultSet res = prSt.executeQuery();
            res.next();
            Post post = new Post();
            post.setId(res.getInt(1));
            post.setName(res.getString(2));
            post.setSalary(res.getDouble(3));
            post.setUnivId(res.getInt(4));

            return post;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Post> getAll() {

        String ins = "SELECT * FROM " + POST_TABLE;
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            ResultSet res = prSt.executeQuery();
            List<Post> posts = new ArrayList<>();
            while (res.next()) {
                Post post = new Post();
                post.setId(res.getInt(1));
                post.setName(res.getString(2));
                post.setSalary(res.getDouble(3));
                post.setUnivId(res.getInt(4));
                posts.add(post);
            }
            return posts;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Post create(Post add) throws ClassNotFoundException {
        Post agr = add;
        String ins = "INSERT INTO " + POST_TABLE + "(" + POST_ID + "," + POST_NAME + "," + POST_SALARY + "," + POST_UNIVERSITY_ID + "," + ")" + "VALUES(?,?,?,?)";
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            prSt.setInt(1, agr.getId());
            prSt.setString(2, agr.getName());
            prSt.setDouble(3, agr.getSalary());
            prSt.setInt(4, agr.getUnivId());
            prSt.executeUpdate();
            return agr;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Post update(Post upd) throws ClassNotFoundException, SQLException {
        Post agr = upd;
        if (getById(agr) != null) {
            ConnectJDBC con = ConnectJDBC.getInstance();
            String ins = "UPDATE " + POST_TABLE + " SET " + POST_ID + " = ?, " + POST_NAME + " =?, " + POST_SALARY + " =?, " + POST_UNIVERSITY_ID + " =? WHERE " + POST_ID + "=" + agr.getId();

            try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
                prSt.setString(1, String.valueOf(agr.getId()));
                prSt.setString(2, agr.getName());
                prSt.setDouble(3, agr.getSalary());
                prSt.setInt(4, agr.getUnivId());
                prSt.executeUpdate();
                return agr;
            } catch (SQLException | ClassNotFoundException e) {
                return null;
            }
        } else create(upd);
        return null;
    }

    public boolean delete(Post del) throws SQLException, ClassNotFoundException {
        int realID = del.getId();
        if (getById(del) != null) {
            ConnectJDBC con = ConnectJDBC.getInstance();
            String ins = "DELETE FROM " + POST_TABLE + " WHERE " + POST_ID + "=?";
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

    public static synchronized PostRepository getInstance() {
        if (instance == null) instance = new PostRepository();
        return instance;
    }
}
