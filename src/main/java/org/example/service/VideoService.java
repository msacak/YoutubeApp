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

import java.util.List;
import java.util.Optional;

public class VideoService {
    private VideoRepository videoRepository;
    private UserService userService;
    private String sql;

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
                video.setDescription(videoSaveRequestDTO.getDescription());
                video.setTitle(videoSaveRequestDTO.getTitle());

                video.setUser_id(userOpt.get().getId());
                videoRepository.save(video);
                ConsoleTextUtils.printSuccessMessage(video.getTitle() + " başlıklı video başarıyla kaydedildi.");
                videoResponseDTO.setDescription(video.getDescription());
                videoResponseDTO.setTitle(video.getTitle());
                videoResponseDTO.setUsername(userOpt.get().getUsername());
            }

        } catch (Exception e) {
            ConsoleTextUtils.printErrorMessage("VideoService: Video kaydedilirken bir hata oluştu.");
        }
        return Optional.of(videoResponseDTO);
    }


    public void update(VideoUpdateRequestDTO videoUpdateRequestDTO) {
        Video video;

        try {
            Optional<User> userOpt = userService.findByUsernameAndPassword(videoUpdateRequestDTO.getUsername(), videoUpdateRequestDTO.getPassword());

            if (userOpt.isPresent()) {
                video = new Video();
                video.setId(videoUpdateRequestDTO.getId());
                video.setDescription(videoUpdateRequestDTO.getDescription());
                video.setTitle(videoUpdateRequestDTO.getTitle());
                video.setDescription(videoUpdateRequestDTO.getDescription());
                video.setUser_id(userOpt.get().getId());
                videoRepository.update(video);
                ConsoleTextUtils.printSuccessMessage("Service: Video başarıyla güncellendi.");

            }
        } catch (Exception e) {
            ConsoleTextUtils.printErrorMessage("Service: Video güncellenirken hata oluştu.");
        }


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

    public List<Video> getTrendVideos(){
       List<Video> videoList = videoRepository.getTrendVideos();
       if(videoList.isEmpty()){
           ConsoleTextUtils.printErrorMessage("Görüntülenecek video bulunamadi");
       }
       return videoList;
    }
}
