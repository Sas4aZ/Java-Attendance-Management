package org.example;

import org.example.model.ModelAttendance;
import org.example.model.ModelClass;
import org.example.model.ModelUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBcontroller {
    //table names
    public static final String TABLE_USER = "user";
    public static final String TABLE_ATTENDANCE = "attendance";
    public static final String TABLE_CLASS = "class";
    //users table
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PASSWORD = "password";

    //class table
    public static final String COLUMN_CLASSNAME = "classname";
    public static final String COLUMN_CLASSID = "classid";
    public static final  String COLUMN_USERID = "userid";


//attendance table

    public static Connection connect() {

        Connection connect = null;

        String url = "jdbc:sqlite:src/main/resources/database/attendance_management.db";

        try {
            connect = DriverManager.getConnection(url);
            System.out.println("connected");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return connect;
    }

    public static void addUsers(ModelUser modeUser, Connection connection) {

        String sql = "INSERT INTO " + TABLE_USER + "(" + COLUMN_USERNAME + "," + COLUMN_PASSWORD + ") " +
                "VALUES(?,?)";

//        insert into table with prepared statement. the first line prepares database for data, second one provides the data.
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, modeUser.getUsername());
            pstmt.setString(2, modeUser.getPassword());
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public static void addClass(ModelClass modelClass, Connection connection) {

        String sql = "INSERT INTO " + TABLE_CLASS + "(" + COLUMN_CLASSNAME + ") " +
                "VALUES(?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, modelClass.getClassname());
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addAttendance(ModelAttendance modelAttendance, Connection connection) {

        String sql = "INSERT INTO " + TABLE_ATTENDANCE + "("+ COLUMN_CLASSID + "," + COLUMN_USERID +") " +
                "VALUES(?,?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, modelAttendance.getClassid());
            pstmt.setInt(2, modelAttendance.getUserid());
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static int getClassIdFromClass(Connection connection, String classname) {
int class_id=0;
        String query = "SELECT id FROM class WHERE classname = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, classname);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                 class_id = rs.getInt("id");
            } else {
                System.out.println("User " + classname + " not found.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return class_id;
    }


    public static int getUserIdFromUser(Connection connection, String username) {
        int user_id = 0;
        String query = "SELECT id FROM user WHERE username = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                 user_id = rs.getInt("id");
            } else {
                System.out.println("User " + username + " not found.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user_id;
    }

    public static List<ModelClass> getClasses(Connection connection) {
        String query = "SELECT * FROM " + TABLE_CLASS ;
        List<ModelClass> classesArrayList = new ArrayList<ModelClass>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt(COLUMN_ID);
                String classname = resultSet.getString(COLUMN_CLASSNAME);

                classesArrayList.add( new ModelClass(id,classname) );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return classesArrayList;
    }
    public static List<ModelUser> getUser(Connection connection) {
        String sql = "SELECT * FROM " + TABLE_USER ;
        List<ModelUser> userArrayList = new ArrayList<ModelUser>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt(COLUMN_ID);
                String username = resultSet.getString(COLUMN_USERNAME);

                userArrayList.add( new ModelUser(id,username));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("User added");
        return userArrayList;
    }

}

