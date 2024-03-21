package com.suaimori.backend.repository;

import com.suaimori.backend.model.entities.Club;
import com.suaimori.backend.model.entities.ClubMember;
import com.suaimori.backend.model.entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClubMemberRepository extends JpaRepository<ClubMember, Long>{
    Optional<ClubMember> findByUserAndClub(User user, Club club);
    List<ClubMember> findByUser(User user);
    List<ClubMember> findByClub(Club club);
    List<ClubMember> findByClubAndIsAdmin(Club club, Boolean isAdmin);
    List<ClubMember> findByUserAndIsAdmin(User user, Boolean isAdmin);
    void deleteByUserAndClub(User user, Club club);
    void deleteByClub(Club club);
    void deleteByUser(User user);
    void deleteByUserAndIsAdmin(User user, Boolean isAdmin);
    void deleteByClubAndIsAdmin(Club club, Boolean isAdmin);
    void deleteByUserAndClubAndIsAdmin(User user, Club club, Boolean isAdmin);
}
