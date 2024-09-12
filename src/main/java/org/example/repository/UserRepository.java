package org.example.repository;

import org.example.entity.User;
import org.example.entity.Video;
import org.example.utility.ConnectionProvider;
import org.example.utility.ConsoleTextUtils;
import org.example.utility.ICRUD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements ICRUD<User> {
    private final ConnectionProvider connectionProvider;
    private String sql;

    public UserRepository() {
        this.connectionProvider = ConnectionProvider.getInstance();
    }


    @Override
    public Optional<User> save(User user) {
        sql = "INSERT INTO tbluser " +
                "(name,surname,email,username,password) VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            ConsoleTextUtils.printErrorMessage("UserRepository: Kullanıcı kaydedilirken hata oluştu.");
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> update(User user) {
        sql = "UPDATE tbluser SET name=?,surname=?,email=?,username=?,password=? WHERE id=?";
        try(PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setLong(6, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
           ConsoleTextUtils.printErrorMessage("UserRepository: Kullanıcı güncellenirken hata oluştu.");
        }
        return Optional.ofNullable(user);
    }

    @Override
    public void delete(Long silinecekUserId) {
        sql = "DELETE FROM tbluser WHERE id = ?";
        try(PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
            preparedStatement.setLong(1, silinecekUserId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            ConsoleTextUtils.printErrorMessage("UserRepository: Kullanıcı silinirken hata oluştu.");
        }
    }

    @Override
    public List<User> findAll() {
        sql = "SELECT * FROM tbluser";
        List<User> userList = new ArrayList<>();
        try(PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)){
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String username = rs.getString("username");
                String password = rs.getString("password");

                userList.add(new User(id, name, surname, email, username, password));
            }

        } catch (SQLException e) {
            ConsoleTextUtils.printErrorMessage("UserRepository: Kullanıcı listesi görüntülenirken hata oluştu.");
        }
        return userList;
    }

    @Override
    public Optional<User> findById(Long id) {
        sql = "SELECT * FROM tbluser WHERE id=?";
        try(PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)){
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String username = rs.getString("username");
                String password = rs.getString("password");
                return Optional.of(new User(id, name, surname, email, username, password));
            }
        } catch (SQLException e) {
            ConsoleTextUtils.printErrorMessage("UserRepository: Aradığınız kullanıcı bulunurken hata oluştu.");
        }
        return Optional.empty();
    }

    public boolean isUsernameExist(String username) {
        sql = "SELECT * FROM tbluser WHERE username=?";
        try(PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)){
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            ConsoleTextUtils.printErrorMessage("Kullanıcı aranırken hata oluştu.");
        }
    return false;
    }
    public boolean isMailExist(String email) {
        sql = "SELECT * FROM tbluser WHERE email=?";
        try(PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)){
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            ConsoleTextUtils.printErrorMessage("UserRepository: Kullanıcı aranırken hata oluştu.");
        }
        return false;
    }

    public boolean isUsernameAndMailExist(String username, String mail) {
        sql = "SELECT * FROM tbluser WHERE username=? AND email=?";
        try(PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)){
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,mail);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            ConsoleTextUtils.printErrorMessage("UserRepository: Kullanıcı aranırken hata oluştu.");
        }
        return false;
    }

    public Optional<User> findByUsername(String username) {
        sql = "SELECT * FROM tbluser WHERE username=?";
        try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
            preparedStatement.setString(1,username);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String password = rs.getString("password");
                return Optional.of(new User(id, name, surname, email, username, password));
            }
        } catch (SQLException e) {
            ConsoleTextUtils.printErrorMessage("UserRepository: Kullanıcı aranırken hata oluştu.");
        }
        return Optional.empty();
    }
    public Optional<User> findByUsernameAndPassword(String username, String password)  {
        sql = "SELECT * FROM tbluser WHERE username=? AND password=?";
        try(PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)){
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                return Optional.of(new User(id, name, surname, email, username, password));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public List<Video> getVideosOfUser(User user){
        sql = "SELECT * FROM tblvideo WHERE user_id=?";
        List<Video> videoList = new ArrayList<>();
        try(PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)){
            preparedStatement.setLong(1, user.getId());
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Long id = rs.getLong("id");
                Long user_id = rs.getLong("user_id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                videoList.add(new Video(id,user_id,title,description));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return videoList;
    }



}
