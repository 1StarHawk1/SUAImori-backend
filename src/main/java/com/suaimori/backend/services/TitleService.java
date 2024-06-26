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
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Selection;
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
    @PersistenceContext
    private EntityManager entityManager;

    public Title create(TitleDTO titleDTO) throws ChangeSetPersister.NotFoundException {
        Title title = new Title(titleDTO);

        if(title.getAuthors() != null){
            List<Author> authors = new ArrayList<>();
            for (AuthorForTitleDTO author : titleDTO.getAuthors()) {
                authors.add(authorRepository.findByFirstNameAndSecondName(author.getFirstName(), author.getSecondName()).orElseThrow(
                        () -> new ChangeSetPersister.NotFoundException()
                ));
            }
            title.setAuthors(authors);
        }

        if(title.getMediaCompanies() != null){
            List<MediaCompany> mediaCompanies = new ArrayList<>();
            for (MediaCompanyForTitleDTO mediaCompany : titleDTO.getMediaCompanies()) {
                mediaCompanies.add(mediaCompanyRepository.findByName(mediaCompany.getName()).orElseThrow(
                        () -> new ChangeSetPersister.NotFoundException()
                ));
            }
            title.setMediaCompanies(mediaCompanies);
        }

        if(!titleDTO.getFranchise().getName().equals("N/A")){
            title.setFranchise(franchiseRepository.findByName(titleDTO.getFranchise().getName()).orElseThrow(
                    () -> new ChangeSetPersister.NotFoundException()
            ));
        }
        if (titleRepository.findByName(title.getName()).isPresent() && titleRepository.findByType(title.getType()).contains(title)) {
            throw new RuntimeException("Title already exists");
        }
        return titleRepository.save(title);
    }


    public Title findByName(String name) {
        return titleRepository.findByName(name).orElseThrow(() -> new RuntimeException("Title not found"));
    }

    public void delete(Long id) {
        titleRepository.deleteById(id);
    }

    public Title findById(Long titleId) {
        return titleRepository.findById(titleId)
                .orElseThrow(() -> new RuntimeException("Title not found"));
    }

    public void update(Long id, TitleDTO titleDTO) {
        Title title = findById(id);
        title.updateFromDto(titleDTO);

        List<Author> authors = new ArrayList<>();
        for (AuthorForTitleDTO author : titleDTO.getAuthors()) {
            authors.add(authorRepository.findByFirstNameAndSecondName(author.getFirstName(), author.getSecondName()).orElseThrow(
                    () -> new RuntimeException("Author not found")
            ));
        }
        title.setAuthors(authors);

        List<MediaCompany> mediaCompanies = new ArrayList<>();
        for (MediaCompanyForTitleDTO mediaCompany : titleDTO.getMediaCompanies()) {
            mediaCompanies.add(mediaCompanyRepository.findByName(mediaCompany.getName()).orElseThrow(
                    () -> new RuntimeException("Media Company not found")
            ));
        }
        title.setMediaCompanies(mediaCompanies);

        title.setFranchise(franchiseRepository.findByName(titleDTO.getFranchise().getName()).orElseThrow(
                () -> new RuntimeException("Franchise not found")
        ));

        titleRepository.save(title);
    }

    public List<Tuple> getTitle(Long id, List<String> fields) {
        fields.add("id");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
        Root<Title> root = cq.from(Title.class);

        List<Selection<?>> selections = new ArrayList<>();
        for (String field : fields) {
            selections.add(root.get(field).alias(field));
        }
        cq.multiselect(selections);
        cq.where(cb.equal(root.get("id"), id));

        TypedQuery<Tuple> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    public List<Long> getAllId() {
        return titleRepository.findAllIds();
    }

    public List<Long> getAllAnimeId() {
        return titleRepository.findAllAnimeIds();
    }

    public List<Long> getAllMangaId() {
        return titleRepository.findAllMangaIds();
    }

    public List<Long> getOngoingAnimeId() {
        return titleRepository.findOngoingAnimeIds();
    }
}
