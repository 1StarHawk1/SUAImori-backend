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

    public Author create(Author author){
        if(authorRepository.findByNickname(author.getNickname()).isPresent()){
            throw new RuntimeException("Author already exists");
        }
        if(authorRepository.findByFirstNameAndSecondName(author.getFirstName(), author.getSecondName()).isPresent()){
            throw new RuntimeException("Author already exists");
        }
        return saveAuthor(author);
    }

    public Author findById(Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isPresent()) {
            return authorOptional.get();
        } else {
            throw new RuntimeException("Author not found with id: " + id);
        }
    }

    private Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author convertToEntity(AuthorDTO authorDTO) {
        Author author = new Author(authorDTO);
        return author;
    }

    @Transactional
    public void delete(Long id) {
        authorRepository.deleteAuthorWithTitles(id);
        authorRepository.deleteById(id);
    }
}
