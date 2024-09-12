package org.example.service;

import org.example.dto.request.VideoRequestDTO;
import org.example.dto.response.UserResponseDTO;
import org.example.dto.response.VideoResponseDTO;
import org.example.entity.User;
import org.example.entity.Video;
import org.example.repository.VideoRepository;
import org.example.utility.ConsoleTextUtils;
import org.example.utility.ICRUD;

import java.util.List;
import java.util.Optional;

public class VideoService   {
    private VideoRepository videoRepository;
    private UserService userService;
    private String sql;

    public VideoService( ) {
        this.videoRepository = new VideoRepository();
        this.userService = new UserService();
    }


    public Optional<VideoResponseDTO> save(VideoRequestDTO videoRequestDTO) {
        Video video;
        VideoResponseDTO videoResponseDTO = new VideoResponseDTO();
        Optional<User> userOpt = userService.findByUsernameAndPassword(videoRequestDTO.getUsername(), videoRequestDTO.getPassword());
        try {
            if (userOpt.isPresent()){
                video = new Video();
                video.setDescription(videoRequestDTO.getDescription());
                video.setTitle(videoRequestDTO.getTitle());
                video.setViews(videoRequestDTO.getViews());
                video.setUser_id(userOpt.get().getId());
                videoRepository.save(video);
                ConsoleTextUtils.printSuccessMessage(video.getTitle()+" başlıklı video başarıyla kaydedildi.");
                videoResponseDTO.setDescription(video.getDescription());
                videoResponseDTO.setTitle(video.getTitle());
                videoResponseDTO.setViews(videoRequestDTO.getViews());
                videoResponseDTO.setUsername(userOpt.get().getUsername());
            }

        } catch (Exception e) {
            ConsoleTextUtils.printErrorMessage("VideoService: Video kaydedilirken bir hata oluştu.");
        }
        return Optional.of(videoResponseDTO);
    }


    public Optional<VideoResponseDTO> update(VideoRequestDTO videoRequestDTO) {
        Optional<User> user = userService.findByUsernameAndPassword(videoRequestDTO.getUsername(), videoRequestDTO.getPassword());
        Video video;

        if(user.isPresent()) {

        }

        return Optional.empty();
    }//todo: controllerdan sonra yapılacak.


    public void delete(Long id) {
        videoRepository.delete(id);
        ConsoleTextUtils.printSuccessMessage("Video başarıyla silindi.");
    }


    public List<Video> findAll() {
        return videoRepository.findAll();
    }


    public Optional<Video> findById(Long id) {
        return videoRepository.findById(id);
    }
}
