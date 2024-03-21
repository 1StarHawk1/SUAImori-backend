package com.suaimori.backend.services;

import com.suaimori.backend.model.entities.Club;
import com.suaimori.backend.model.entities.ClubMember;
import com.suaimori.backend.model.entities.User;
import com.suaimori.backend.repository.ClubMemberRepository;
import com.suaimori.backend.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;
    private final ClubMemberRepository clubMemberRepository;

        public Club create(Club club, User creator){
            if(clubRepository.findByName(club.getName()).isPresent()){
                throw new RuntimeException("Club already exists");
            }
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

}
