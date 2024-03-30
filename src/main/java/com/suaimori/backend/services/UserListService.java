package com.suaimori.backend.services;

import com.suaimori.backend.model.dto.UserListDTO;
import com.suaimori.backend.model.entities.User;
import com.suaimori.backend.model.entities.UserList;
import com.suaimori.backend.repository.TitleRepository;
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
    private TitleRepository titleRepository;

    public UserList create(UserListDTO userListDTO, User creator) throws ChangeSetPersister.NotFoundException {
        UserList userList = new UserList(userListDTO);
        userList.setUser(creator);

//        userList.setUser(userRepository.findByUsername(userListDTO.getUser().getUsername()).orElseThrow(
//                ChangeSetPersister.NotFoundException::new));
        return userListRepository.save(userList);
    }

    public void addTitle(Long ListId, Long titleId){
        UserList userList = userListRepository.findById(ListId).orElseThrow(() -> new RuntimeException("UserList not found"));
        userList.addTitle(titleRepository.findById(titleId).orElseThrow(() -> new RuntimeException("Title not found")));
        userListRepository.save(userList);
    }

    public void hide(Long id) {
        UserList userList = userListRepository.findById(id).orElseThrow(() -> new RuntimeException("UserList not found"));
        userList.setIsVisible(false);
        userListRepository.save(userList);
    }

    public void show(Long id) {
        UserList userList = userListRepository.findById(id).orElseThrow(() -> new RuntimeException("UserList not found"));
        userList.setIsVisible(true);
        userListRepository.save(userList);
    }

    public void delete(Long id) {
        userListRepository.deleteById(id);
    }

    public void update(Long id, UserListDTO userListDTO) {
        UserList userList = userListRepository.findById(id).orElseThrow(() -> new RuntimeException("UserList not found"));
        userList.setName(userListDTO.getName());
        userListRepository.save(userList);
    }

    public void removeTitle(Long ListId, Long titleId) {
        UserList userList = userListRepository.findById(ListId).orElseThrow(() -> new RuntimeException("UserList not found"));
        userList.removeTitle(titleRepository.findById(titleId).orElseThrow(() -> new RuntimeException("Title not found")));
        userListRepository.save(userList);
    }
}
