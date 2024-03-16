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

    public MediaCompany create(MediaCompany mediaCompany){
        if(mediaCompanyRepository.findByName(mediaCompany.getName()).isPresent()){
            throw new RuntimeException("Media Company already exists");
        }
        return saveMediaCompany(mediaCompany);
    }

    public MediaCompany findById(Long id) {
        Optional<MediaCompany> mediaCompanyOptional = mediaCompanyRepository.findById(id);
        if (mediaCompanyOptional.isPresent()) {
            return mediaCompanyOptional.get();
        } else {
            throw new RuntimeException("Media Company not found with id: " + id);
        }
    }

    private MediaCompany saveMediaCompany(MediaCompany mediaCompany) {
        return mediaCompanyRepository.save(mediaCompany);
    }

    public MediaCompany convertToEntity(MediaCompanyDTO medaCompanyDTO) {
        MediaCompany mediaCompany = new MediaCompany(medaCompanyDTO);
        return mediaCompany;
    }
}
