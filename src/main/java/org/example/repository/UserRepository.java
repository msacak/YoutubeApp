package org.example.repository;

import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.example.entity.User;
import org.example.entity.Video;
import org.example.utility.ConnectionProvider;
import org.example.utility.ConsoleTextUtils;
import org.example.utility.HibernateConnection;
import org.example.utility.ICRUD;
import org.hibernate.HibernateException;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements ICRUD<User> {
    private String hql;

    @Override
    public Optional<User> save(User user) {
        HibernateConnection.entityManager.persist(user);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> update(User user) {
        User updatedUser = HibernateConnection.entityManager.merge(user);
        return Optional.ofNullable(updatedUser);
    }

    @Override
    public boolean delete(Long silinecekUserId) { //TODO: Bu şuan HardDelete çalışıyor, buna bakılacak soft delete çevirilmeli mi ??
        hql = "DELETE FROM User u WHERE u.id = :userId";
        Query query = HibernateConnection.entityManager.createQuery(hql);
        query.setParameter("userId", silinecekUserId);

        try {
           int result = query.executeUpdate();
           return result>0;
        } catch (Exception e) {
            ConsoleTextUtils.printErrorMessage("UserRepository: Kullanıcı silinirken hata oluştu.");
            return false;
        }

    }

    @Override
    public List<User> findAll() {
        hql = "FROM User";

        List<User> userList = new ArrayList<>();
        try{
            userList = HibernateConnection.entityManager.createQuery(hql,User.class).getResultList();
        } catch (Exception e) {
            ConsoleTextUtils.printErrorMessage("UserRepository: Kullanıcı listesi görüntülenirken hata oluştu.");

        }
        return userList;
    }

    @Override
    public Optional<User> findById(Long id) {
        hql = "FROM User u WHERE u.id = :id";

        try{
            Query query = HibernateConnection.entityManager.createQuery(hql,User.class);
            query.setParameter("id", id);
            User user = (User) query.getSingleResult();
            return Optional.ofNullable(user);

        }catch(NoResultException e) { //Hiçbir sonuç dönmezse genel bir exception hatası vermesin, Optional.Empty dönsün
            return Optional.empty();
        }
        catch (HibernateException e) {
            ConsoleTextUtils.printErrorMessage("UserRepository: Aradığınız kullanıcı bulunurken hata oluştu.");
        }
        return Optional.empty();
    }

    public boolean isUsernameExist(String username) {
        hql = "SELECT COUNT(u) FROM User u WHERE u.username= :username";
        Long count = HibernateConnection.entityManager.createQuery(hql, Long.class)
                .setParameter("username", username)
                .getSingleResult();
        return count>0;


    }
    public boolean isMailExist(String email) {
        try {
            // JPQL sorgusu ile e-posta adresini kontrol ediyoruz
            hql = "SELECT COUNT(u) FROM User u WHERE u.email = :email";

            // Hibernate EntityManager kullanarak sorguyu oluşturuyoruz
            Long count = HibernateConnection.entityManager.createQuery(hql, Long.class)
                    .setParameter("email", email)
                    .getSingleResult();

            // E-posta adresi varsa count 0'dan büyük olacak
            return count > 0;

        } catch (Exception e) {
            ConsoleTextUtils.printErrorMessage("UserRepository: Kullanıcı aranırken hata oluştu. " + e.getMessage());
            return false;
        }
    }


    public boolean isUsernameAndMailExist(String username, String mail) {
        hql = "SELECT COUNT(u) FROM User u WHERE u.username = :username AND u.email = :mail";
        try{
            Long count  = HibernateConnection.entityManager.createQuery(hql,Long.class)
                    .setParameter("username",username)
                    .setParameter("mail",mail).getSingleResult();
            return count>0;

        } catch (Exception e) {
            ConsoleTextUtils.printErrorMessage("UserRepository: Kullanıcı aranırken hata oluştu.");
        }
        return false;
    }

    public Optional<User> findByUsername(String username) {
        hql = "FROM User u WHERE u.username= :username";
        Query query = HibernateConnection.entityManager.createQuery(hql, User.class);
        query.setParameter("username", username);

        try {
            User user = (User) query.getSingleResult();
            return Optional.of(user);

        }catch(NoResultException e){
            return Optional.empty();
        }
        catch (Exception e) {
            ConsoleTextUtils.printErrorMessage("UserRepository: Kullanıcı aranırken hata oluştu. ");
            e.printStackTrace();
        }
        return Optional.empty();
    }
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        try {
            // HQL sorgusu: JPQL kullanarak User nesnesini arıyoruz
            String hql = "FROM User u WHERE u.username = :username AND u.password = :password";

            // Hibernate EntityManager kullanarak sorguyu oluşturuyoruz
            User user = HibernateConnection.entityManager.createQuery(hql, User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();

            // Eğer kullanıcı bulunduysa, Optional ile döndürüyoruz
            return Optional.ofNullable(user);

        }catch(NoResultException e){
            return Optional.empty();
        }
        catch (Exception e) {
            ConsoleTextUtils.printErrorMessage("Repository : Bir hata oluştu. " + e.getMessage());
        }

        return Optional.empty();
    }





}
