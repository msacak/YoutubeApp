package org.example;

import org.example.dto.request.UserRequestDto;
import org.example.dto.response.UserResponseDTO;
import org.example.entity.Comment;
import org.example.entity.Like;
import org.example.entity.User;
import org.example.entity.Video;
import org.example.gui.MainGUI;
import org.example.repository.CommentRepository;
import org.example.repository.LikeRepository;
import org.example.repository.UserRepository;
import org.example.repository.VideoRepository;
import org.example.service.UserService;

import java.util.Optional;

public class Runner {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        VideoRepository videoRepository = new VideoRepository();
        CommentRepository commentRepository = new CommentRepository();
        LikeRepository likeRepository = new LikeRepository();

        UserService userService = new UserService();

        MainGUI mainGUI = new MainGUI();
        mainGUI.mainMenuOptions(mainGUI.mainMenu());







    }
}