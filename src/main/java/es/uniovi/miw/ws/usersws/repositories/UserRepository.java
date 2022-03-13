package es.uniovi.miw.ws.usersws.repositories;

import es.uniovi.miw.ws.usersws.models.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.lang.System.getProperties;


public class UserRepository {
    static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://156.35.98.103:3306/ws_miw_database";
    static final String DB_USERNAME = "root";
    static final String DB_PASSWORD = "ws_miw_password";

    Connection connection;
    Properties properties;

    public UserRepository() {
        initialize();
    }

    public void initialize() {
        try {
            Class.forName(DB_DRIVER);
            this.connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (Exception e) {
            System.out.println("Algo has liado con la bd campeón");
            e.printStackTrace();
        }
    }

    public User addUser(User user) {
        try {
            properties = new Properties();
            properties.load(getClass().getResourceAsStream("/META-INF/queries.properties"));
            String query = properties.getProperty("query.addUser");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getAddress());
            preparedStatement.setBoolean(4, user.isAnonymous());

            int count = preparedStatement.executeUpdate();
            if (count == 1) {
                return user;
            }
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null)
                try {
                    connection.close();
                } catch (Exception e) {
                }
        }
    }

    public User findById(Long id) {
        try {
            properties = new Properties();
            properties.load(getClass().getResourceAsStream("/META-INF/queries.properties"));
            String query = properties.getProperty("query.findById");
//            String query = "SELECT id, username, password, address, isAnonymous FROM users " +
//                    "WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            User user = new User();
            while (rs.next()) {
                user.setId(rs.getLong(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setAddress(rs.getString(4));
                user.setAnonymous(rs.getBoolean(5));
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null)
                try {
                    connection.close();
                } catch (Exception e) {
                }
        }
    }

    public User updateUser(String username, String password, String address, boolean isAnonymous) {
        try {
            properties = new Properties();
            properties.load(getClass().getResourceAsStream("/META-INF/queries.properties"));
            String query = properties.getProperty("query.updateUser");
//            String query = "UPDATE users SET username = ?, password = ?, address = ?, isAnonymous= ? WHERE username =" +
//                    " ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, address);
            preparedStatement.setBoolean(4, isAnonymous);
            preparedStatement.setString(5, username);
            int count = preparedStatement.executeUpdate();
            if (count == 1) {
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setAnonymous(isAnonymous);
                user.setAddress(address);
                return user;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null)
                try {
                    connection.close();
                } catch (Exception e) {
                }
        }
    }

    public User deleteUser(Long id) {
        try {
            properties = new Properties();
            properties.load(getClass().getResourceAsStream("/META-INF/queries.properties"));
            String query = properties.getProperty("query.deleteUser");
//            String query = "DELETE FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            User user = new User();
            while (rs.next()) {
                user.setId(rs.getLong(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setAddress(rs.getString(4));
                user.setAnonymous(rs.getBoolean(5));
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null)
                try {
                    connection.close();
                } catch (Exception e) {
            }
        }
    }

    public List<User> findAll() {
        try {
            properties = new Properties();
            properties.load(getClass().getResourceAsStream("/META-INF/queries.properties"));
            String query = properties.getProperty("query.findAll");
//            String query = "SELECT id, username, password, address, isAnonymous FROM users";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            List<User> users = new ArrayList<User>();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setAddress(rs.getString(4));
                user.setAnonymous(rs.getBoolean(5));
                users.add(user);
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null)
                try {
                    connection.close();
                } catch (Exception e) {
            }
        }
    }

    public User findByUsername(String username) {
        try {
            properties = new Properties();
            properties.load(getClass().getResourceAsStream("/META-INF/queries.properties"));
            String query = properties.getProperty("query.findByUsername");
//            String query = "SELECT id, username, password, address, isAnonymous FROM users WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            User user = new User();
            while (rs.next()) {
                user.setId(rs.getLong(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setAddress(rs.getString(4));
                user.setAnonymous(rs.getBoolean(5));
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null)
                try {
                    connection.close();
                } catch (Exception e) {
            }
        }
    }
}
