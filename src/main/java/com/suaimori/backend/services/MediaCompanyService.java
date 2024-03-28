package com.suaimori.backend.services;

import com.suaimori.backend.model.dto.MediaCompanyDTO;
import com.suaimori.backend.model.entities.Author;
import com.suaimori.backend.model.entities.MediaCompany;
import com.suaimori.backend.repository.MediaCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MediaCompanyService {

    private final MediaCompanyRepository mediaCompanyRepository;

    public void create(MediaCompanyDTO mediaCompanyDTO){
        if(mediaCompanyRepository.findByName(mediaCompanyDTO.getName()).isPresent()){
            throw new RuntimeException("Media Company already exists");
        }
        MediaCompany mediaCompany = new MediaCompany(mediaCompanyDTO);
        mediaCompanyRepository.save(mediaCompany);
    }

    public MediaCompany findById(Long id) {
        Optional<MediaCompany> mediaCompanyOptional = mediaCompanyRepository.findById(id);
        if (mediaCompanyOptional.isPresent()) {
            return mediaCompanyOptional.get();
        } else {
            throw new RuntimeException("Media Company not found with id: " + id);
        }
    }


    public void delete(Long id){
        mediaCompanyRepository.deleteById(id);
    }

    public void update(Long id, MediaCompanyDTO mediaCompanyDTO) {
        MediaCompany mediaCompany = findById(id);
        mediaCompany.updateFromDto(mediaCompanyDTO);
        mediaCompanyRepository.save(mediaCompany);
    }
}
