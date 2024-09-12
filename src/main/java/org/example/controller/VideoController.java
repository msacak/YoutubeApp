package org.example.controller;

import org.example.dto.request.VideoRequestDTO;
import org.example.dto.response.VideoResponseDTO;
import org.example.entity.Video;
import org.example.utility.ICRUD;

import java.util.List;
import java.util.Optional;

public class VideoController  {


    public Optional<VideoResponseDTO> save(VideoRequestDTO videoRequestDTO) {
        return Optional.empty();
    }


    public Optional<VideoRequestDTO> update(VideoRequestDTO videoRequestDTO) {
        return Optional.empty();
    }


    public void delete(Long id) {

    }


    public List<VideoRequestDTO> findAll() {
        return List.of();
    }

    public Optional<VideoRequestDTO> findById(Long id) {
        return Optional.empty();
    }
}
