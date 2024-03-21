package com.suaimori.backend.services;

import com.suaimori.backend.model.dto.UserListDTO;
import com.suaimori.backend.model.entities.UserList;
import com.suaimori.backend.repository.UserListRepository;
import com.suaimori.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserListService {
    private UserListRepository userListRepository;
    private UserRepository userRepository;

    public UserList create(UserListDTO userListDTO) throws ChangeSetPersister.NotFoundException {
        UserList userList = new UserList(userListDTO);

        userList.setUser(userRepository.findByUsername(userListDTO.getUser().getUsername()).orElseThrow(
                ChangeSetPersister.NotFoundException::new));
        return userListRepository.save(userList);
    }
}
