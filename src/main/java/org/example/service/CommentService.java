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
        try{
            if(commentOptional.isPresent()){
                commentRepository.save(commentOptional.get());

            }

        }catch (Exception e){

        }

        return null;
    }


    public Optional<CommentRequestDTO> update(Comment comment) {
        return commentRepository.update(comment)
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
