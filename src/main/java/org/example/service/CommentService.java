package org.example.service;

import org.example.dto.request.CommentRequestDTO;
import org.example.dto.response.CommentResponseDTO;
import org.example.entity.Comment;
import org.example.entity.User;
import org.example.entity.Video;
import org.example.repository.CommentRepository;
import org.example.utility.ICRUD;

import java.util.List;
import java.util.Optional;

public class CommentService  {
    private CommentRepository commentRepository;
    private UserService userService;
    private String sql;

    public CommentService() {
        commentRepository = new CommentRepository();
        userService = new UserService();
    }

    public Optional<CommentResponseDTO> save(CommentRequestDTO commentRequestDTO) {
        Comment comment;
        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
        Optional<User> userOptional = userService.findByUsernameAndPassword(commentRequestDTO.getUsername(), commentRequestDTO.getPassword());
      //  Optional<Video> videoOptional =
       try{
           if(userOptional.isPresent()) {
                comment = new Comment();
                comment.setComment(commentRequestDTO.getComment());
                comment.setUser_id(userOptional.get().getId());

           }
       }catch (Exception e) {
           e.printStackTrace();
       }
       return null;
    }


    public Optional<CommentRequestDTO> update(CommentRequestDTO commentRequestDTO) {
        return Optional.empty();
    }


    public void delete(Long id) {

    }


    public List<Comment> findAll() {
        return List.of();
    }


    public Optional<Comment> findById(Long id) {
        return Optional.empty();
    }
}
