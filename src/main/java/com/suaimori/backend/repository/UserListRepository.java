package com.suaimori.backend.repository;

import com.suaimori.backend.model.entities.UserList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserListRepository extends JpaRepository<UserList, Long> {

    Optional<UserList> findByName(String name);
}
