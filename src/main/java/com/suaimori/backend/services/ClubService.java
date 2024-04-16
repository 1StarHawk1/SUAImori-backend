package com.suaimori.backend.services;

import com.suaimori.backend.model.dto.ClubDTO;
import com.suaimori.backend.model.entities.Club;
import com.suaimori.backend.model.entities.ClubMember;
import com.suaimori.backend.model.entities.Title;
import com.suaimori.backend.model.entities.User;
import com.suaimori.backend.repository.ClubMemberRepository;
import com.suaimori.backend.repository.ClubRepository;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Selection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;
    private final ClubMemberRepository clubMemberRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public Club create(ClubDTO clubDTO, User creator){
        if(clubRepository.findByName(clubDTO.getName()).isPresent()){
            throw new RuntimeException("Club already exists");
        }
        Club club = new Club(clubDTO);

        Club savedClub = clubRepository.save(club);

        ClubMember clubMember = new ClubMember();
        clubMember.setClub(savedClub);
        clubMember.setUser(creator);
        clubMember.setIsAdmin(true);

        clubMemberRepository.save(clubMember); // сохраните ClubMember

        return savedClub;
    }

    public void join(String name, User user){
        Club club = clubRepository.findByName(name).orElseThrow(() -> new RuntimeException("Club not found"));
        ClubMember clubMember = new ClubMember();
        clubMember.setClub(club);
        clubMember.setUser(user);
        clubMember.setIsAdmin(false);
        clubMemberRepository.save(clubMember);
    }

    public void delete(Long id) {
        clubMemberRepository.deleteByClub(clubRepository.findById(id).orElseThrow(() -> new RuntimeException("Club not found")));
        clubRepository.deleteById(id);
    }

    public void leave(String name, User user){
        Club club = clubRepository.findByName(name).orElseThrow(() -> new RuntimeException("Club not found"));
        ClubMember clubMember = clubMemberRepository.findByUserAndClub(user, club).orElseThrow(() -> new RuntimeException("User is not a member of this club"));
        clubMemberRepository.delete(clubMember);
    }

    public void update(Long id, ClubDTO clubDTO) {
        Club club = clubRepository.findById(id).orElseThrow(() -> new RuntimeException("Club not found"));
        club.updateFromDto(clubDTO);
        clubRepository.save(club);
    }

    public List<Long> getAllId() {
        return clubRepository.findAllIds();
    }

    public List<Tuple> getClub(Long id, List<String> fields) {
        fields.add("id");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
        Root<Club> root = cq.from(Club.class);

        List<Selection<?>> selections = new ArrayList<>();
        for (String field : fields) {
            selections.add(root.get(field).alias(field));
        }
        cq.multiselect(selections);
        cq.where(cb.equal(root.get("id"), id));

        TypedQuery<Tuple> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    public Object checkMembership(Long id, User user) {
        return clubMemberRepository.findByUserAndClub(user, clubRepository.findById(id).orElseThrow(() -> new RuntimeException("Club not found"))).isPresent();
    }
}
