package com.suaimori.backend.repository;

import com.suaimori.backend.model.dto.UserListDTO;
import com.suaimori.backend.model.entities.UserList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserListRepository extends JpaRepository<UserList, Long> {

    Optional<UserList> findByName(String name);

    @Query("SELECT new com.suaimori.backend.model.dto.UserListDTO(l.id, l.name, l.isVisible) FROM UserList l WHERE l.user.username = :username")
    List<UserListDTO> getAllUserLists(@Param("username") String username);

    @Query("SELECT t.id FROM Title t JOIN t.userLists l WHERE l.id = :id")
    List<?> getTitles(@Param("id") Long id);
}
