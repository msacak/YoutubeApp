package org.example.repository;

import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.example.entity.User;
import org.example.entity.Video;
import org.example.utility.ConnectionProvider;
import org.example.utility.ConsoleTextUtils;
import org.example.utility.HibernateConnection;
import org.example.utility.ICRUD;
import org.hibernate.HibernateException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VideoRepository implements ICRUD<Video> {
    private final ConnectionProvider connectionProvider;
    private String sql;
    private String hql;

    public VideoRepository() {
        this.connectionProvider = ConnectionProvider.getInstance();
    }

    @Override
    public Optional<Video> save(Video video) {
        try {
            HibernateConnection.entityManager.persist(video);
            return Optional.of(video);

        } catch (Exception e) {
            ConsoleTextUtils.printErrorMessage("VideoRepository: Video kaydedilirken hata oluştu.");
        }
        return Optional.ofNullable(video);
    }

    @Override
    public Optional<Video> update(Video video) {
        Video updatedVideo = HibernateConnection.entityManager.merge(video);
        return Optional.ofNullable(updatedVideo);
    }

    @Override
    public boolean delete(Long silinecekVideoId) {

        hql = "DELETE FROM Video v WHERE v.id = :id";

        try {
            int result = HibernateConnection.entityManager.createQuery(hql)
                    .setParameter("id", silinecekVideoId)
                    .executeUpdate();
            return result > 0;
        } catch (Exception e) {
            ConsoleTextUtils.printErrorMessage("VideoRepository: Video silinirken hata oluştu.");
        }
        return false;
    }

    @Override
    public List<Video> findAll() {
        //(Long user_id, String title, String description)
        hql = "FROM Video";
        List<Video> videoList = new ArrayList<>();
        try {
            videoList = HibernateConnection.entityManager.createQuery(hql, Video.class).getResultList();


        } catch (Exception e) {
            ConsoleTextUtils.printErrorMessage("VideoRepository: Video listesi görüntülenirken hata oluştu.");
        }
        return videoList;
    }

    @Override
    public Optional<Video> findById(Long bulunacakVideoId) {
        hql = "FROM Video v WHERE v.id = :id";
        try {
            Query query = HibernateConnection.entityManager.createQuery(hql, Video.class)
                    .setParameter("id", bulunacakVideoId);
            Video video = (Video) query.getSingleResult();
            return Optional.ofNullable(video);

        } catch (NoResultException e) {
            return Optional.empty();
        } catch (Exception e) {
            ConsoleTextUtils.printErrorMessage("VideoRepository: Aradığınız video bulunurken hata oluştu.");
        }
        return Optional.empty();
    }

    public List<Video> getTrendVideos(){
        hql = "FROM Video v ORDER BY v.views DESC";
        List<Video> videoList = new ArrayList<>();
        try{
            videoList = HibernateConnection.entityManager.createQuery(hql, Video.class).setMaxResults(5).getResultList();

        } catch (Exception e) {
            ConsoleTextUtils.printErrorMessage("VideoRepository: Video listesi görüntülenirken hata oluştu.");
        }
        return videoList;
    }

    public List<Video> findVideosByTitle(String baslik){
        String hql = "FROM Video v WHERE LOWER(v.title) LIKE LOWER(CONCAT('%', :title, '%'))";
        List<Video> videoList = new ArrayList<>();
        try{
            videoList = HibernateConnection.entityManager
                    .createQuery(hql,Video.class)
                    .setParameter("title",baslik)
                    .getResultList();

        } catch (Exception e) {
            ConsoleTextUtils.printErrorMessage("VideoRepository: Video listesi görüntülenirken hata oluştu.");
        }
        return videoList;
    }

    public List<Video> getVideosOfUser(User user){
       hql = "FROM Video v WHERE v.user = :user";
       List<Video> videoList = new ArrayList<>();
       try{
           videoList = HibernateConnection.entityManager.createQuery(hql,Video.class)
                   .setParameter("user",user)
                   .getResultList();

       }catch (Exception e) {
           ConsoleTextUtils.printErrorMessage("VideoRepository: Kullanıcı videoları görüntülenirken hata oluştu.");
       }
       return videoList;
    }


}
