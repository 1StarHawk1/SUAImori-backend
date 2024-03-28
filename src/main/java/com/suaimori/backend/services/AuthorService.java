package com.suaimori.backend.services;

import com.suaimori.backend.model.dto.AuthorDTO;
import com.suaimori.backend.model.entities.Author;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.suaimori.backend.repository.AuthorRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public void create(AuthorDTO authorDTO){
        Author author = new Author(authorDTO);
        authorRepository.save(author);
    }

    public Author findById(Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isPresent()) {
            return authorOptional.get();
        } else {
            throw new RuntimeException("Author not found with id: " + id);
        }
    }


    @Transactional
    public void delete(Long id) {
        authorRepository.deleteAuthorWithTitles(id);
        authorRepository.deleteById(id);
    }

    public void update(Long id, AuthorDTO authorDTO) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
        author.updateFromDto(authorDTO);
        authorRepository.save(author);
    }
}
