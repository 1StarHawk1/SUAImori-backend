package com.suaimori.backend.repository;

import com.suaimori.backend.model.entities.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    Optional<Friend> findByRequestSenderUsername(String username);
    Optional<Friend> findByRequestReceiverUsername(String username);
}
