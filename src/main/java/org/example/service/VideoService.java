package org.example.service;

import org.example.dto.request.VideoRequestDTO;
import org.example.dto.request.VideoSaveRequestDTO;
import org.example.dto.request.VideoUpdateRequestDTO;
import org.example.dto.response.UserResponseDTO;
import org.example.dto.response.VideoResponseDTO;
import org.example.entity.User;
import org.example.entity.Video;
import org.example.repository.VideoRepository;
import org.example.utility.ConsoleTextUtils;
import org.example.utility.ICRUD;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VideoService {
    private VideoRepository videoRepository;
    private UserService userService;

    public VideoService() {
        this.videoRepository = new VideoRepository();
        this.userService = new UserService();
    }


    public Optional<VideoResponseDTO> save(VideoSaveRequestDTO videoSaveRequestDTO) {
        Video video;
        VideoResponseDTO videoResponseDTO = new VideoResponseDTO();
        Optional<User> userOpt = userService.findByUsernameAndPassword(videoSaveRequestDTO.getUsername(), videoSaveRequestDTO.getPassword());
        try {
            if (userOpt.isPresent()) {
                video = new Video();
                video.setUser(userOpt.get());
                video.setDescription(videoSaveRequestDTO.getDescription());
                video.setTitle(videoSaveRequestDTO.getTitle());
                videoRepository.save(video);
                ConsoleTextUtils.printSuccessMessage("Video başarıyla kaydedildi.");
                videoResponseDTO.setUsername(videoSaveRequestDTO.getUsername());
                videoResponseDTO.setDescription(videoSaveRequestDTO.getDescription());
                videoResponseDTO.setTitle(videoSaveRequestDTO.getTitle());
                videoResponseDTO.setViews(video.getViews());
            }
        } catch (Exception e) {
            ConsoleTextUtils.printErrorMessage("VideoService: Video kaydedilirken bir hata oluştu.");
        }
        return Optional.of(videoResponseDTO);
    }


    public void update(VideoUpdateRequestDTO videoUpdateRequestDTO) {
        try {
            Optional<Video> videoOptional = videoRepository.findById(videoUpdateRequestDTO.getId());

            Optional<User> userOptional = userService.findByUsernameAndPassword(videoUpdateRequestDTO.getUsername(), videoUpdateRequestDTO.getPassword());

            if (videoOptional.isPresent() && userOptional.isPresent()) {
                Video video = videoOptional.get();
                video.setDescription(videoUpdateRequestDTO.getDescription());
                video.setTitle(videoUpdateRequestDTO.getTitle());
                video.setUser(userOptional.get());
                video.setViews(video.getViews());
                videoRepository.save(video);
            }

        } catch (Exception e) {
            ConsoleTextUtils.printErrorMessage("VideoService: Video güncellenirken bir hata oluştu.");
        }

    }

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

    public List<Video> getTrendVideos() {
        List<Video> videoList = videoRepository.getTrendVideos();
        if (videoList.isEmpty()) {
            ConsoleTextUtils.printErrorMessage("Görüntülenecek video bulunamadi");
        }
        return videoList;
    }

    public List<Video> findVideosByTitle(String title) {
        List<Video> videoList = videoRepository.findVideosByTitle(title);
        if (videoList.isEmpty()) {
            ConsoleTextUtils.printErrorMessage("Aramaya uygun video bulunamadi.");
        }
        return videoList;
    }

    public List<Video> getVideosOfUser(User user) {
        List<Video> videoList = videoRepository.getVideosOfUser(user);
        if (videoList.isEmpty()) {
            ConsoleTextUtils.printErrorMessage("Görüntülenecek video bulunamadi.");
        }
        return videoList;
    }
}
