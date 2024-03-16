package com.suaimori.backend.services;

import com.suaimori.backend.model.dto.AuthorForTitleDTO;
import com.suaimori.backend.model.dto.MediaCompanyForTitleDTO;
import com.suaimori.backend.model.dto.TitleDTO;
import com.suaimori.backend.model.entities.Author;
import com.suaimori.backend.model.entities.MediaCompany;
import com.suaimori.backend.model.entities.Title;
import com.suaimori.backend.repository.AuthorRepository;
import com.suaimori.backend.repository.FranchiseRepository;
import com.suaimori.backend.repository.MediaCompanyRepository;
import com.suaimori.backend.repository.TitleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TitleService {
    private final TitleRepository titleRepository;
    private final AuthorRepository authorRepository;
    private final MediaCompanyRepository mediaCompanyRepository;
    private final FranchiseRepository franchiseRepository;

    public Title create(TitleDTO titleDTO) throws ChangeSetPersister.NotFoundException {
        Title title = new Title(titleDTO);

        List<Author> authors = new ArrayList<>();
        for(AuthorForTitleDTO author: titleDTO.getAuthors()){
            authors.add(authorRepository.findByFirstNameAndSecondName(author.getFirstName(), author.getSecondName()).orElseThrow(
                    () -> new ChangeSetPersister.NotFoundException()
            ));
        }
        title.setAuthors(authors);

        List<MediaCompany> mediaCompanies = new ArrayList<>();
        for(MediaCompanyForTitleDTO mediaCompany: titleDTO.getMediaCompanies()){
            mediaCompanies.add(mediaCompanyRepository.findByName(mediaCompany.getName()).orElseThrow(
                    () -> new ChangeSetPersister.NotFoundException()
            ));
        }
        title.setMediaCompanies(mediaCompanies);

        title.setFranchise(franchiseRepository.findByName(titleDTO.getFranchise().getName()).orElseThrow(
                () -> new ChangeSetPersister.NotFoundException()
        ));

        if(titleRepository.findByName(title.getName()).isPresent() && titleRepository.findByType(title.getType()).contains(title)){
            throw new RuntimeException("Title already exists");
        }
        return titleRepository.save(title);
    }

    public Title findByName(String name){
        return titleRepository.findByName(name).orElseThrow(() -> new RuntimeException("Title not found"));
    }
}
