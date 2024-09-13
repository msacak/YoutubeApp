package org.example.controller;

import org.example.dto.request.VideoRequestDTO;
import org.example.dto.request.VideoSaveRequestDTO;
import org.example.dto.request.VideoUpdateRequestDTO;
import org.example.dto.response.VideoResponseDTO;
import org.example.entity.Video;
import org.example.service.VideoService;
import org.example.utility.ICRUD;

import java.util.List;
import java.util.Optional;

public class VideoController  {
    private static VideoController videoController;
    private VideoService videoService;

    private VideoController( ) {
        this.videoService = new VideoService();
    }

    public static VideoController getInstance(){
        if(videoController == null) {
            videoController = new VideoController();
        }
        return videoController;
    }


    public Optional<VideoResponseDTO> save(VideoSaveRequestDTO videoSaveRequestDTO) {
        return videoService.save(videoSaveRequestDTO);
    }


    public void update(VideoUpdateRequestDTO videoUpdateRequestDTO) {
        videoService.update(videoUpdateRequestDTO);
    }


    public void delete(Long id) {
        videoService.delete(id);
    }


    public List<Video> findAll() {
        return videoService.findAll();
    }

    public Optional<Video> findById(Long id) {
        return videoService.findById(id);
    }

    public List<Video> getTrendVideos(){
        return videoService.getTrendVideos();
    }

    public List<Video> findVideosByTitle(String title) {
        return videoService.findVideosByTitle(title);
    }
}
