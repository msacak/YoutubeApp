package org.example.repository;

import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.example.entity.Comment;
import org.example.utility.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CommentRepository implements ICRUD<Comment> {

    private String hql;


    @Override
    public Optional<Comment> save(Comment comment) {
        HibernateConnection.entityManager.persist(comment);
        return Optional.ofNullable(comment);
    }

    @Override
    public boolean delete(Long silinecekCommentId) {
        hql = "DELETE FROM Comment c WHERE c.id = :id";
        Query query = HibernateConnection.entityManager.createQuery(hql)
                .setParameter("id", silinecekCommentId);
        try {
            int result = query.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            ConsoleTextUtils.printErrorMessage("Repository : Yorum silinirken hata oluştu. " + e.getMessage());
        }
        return false;
    }

    @Override
    public Optional<Comment> update(Comment comment) {

        Comment updatedComment = HibernateConnection.entityManager.merge(comment);
        return Optional.ofNullable(updatedComment);

    }

    @Override
    public List<Comment> findAll() {
        hql = "FROM Comment";
        List<Comment> commentList = new ArrayList<>();
        try {
            commentList = HibernateConnection.entityManager.createQuery(hql, Comment.class).getResultList();
        } catch (Exception e) {
            ConsoleTextUtils.printErrorMessage("Repository : Yorumlar listelenirken hata oluştu. " + e.getMessage());
        }
        return commentList;
    }

    @Override
    public Optional<Comment> findById(Long bulunacakCommentId) {
        hql = "FROM Comment c WHERE c.id = :id";
        try {
            Comment comment = HibernateConnection.entityManager.createQuery(hql, Comment.class)
                    .setParameter("id", bulunacakCommentId)
                    .getSingleResult();
            return Optional.ofNullable(comment);

        }catch (NoResultException e){
            return Optional.empty();
        }
        catch (Exception e) {
            ConsoleTextUtils.printErrorMessage("UserRepository: Comment aranırken hata oluştu. " + e.getMessage());
        }
        return Optional.empty();
    }
}
