package com.suaimori.backend.services;

import com.suaimori.backend.model.entities.Friend;
import com.suaimori.backend.model.entities.User;
import com.suaimori.backend.repository.FriendRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;

    public Friend create(User creator, User receiver){
        if(friendRepository.findByRequestSenderUsername(creator.getUsername()).isPresent() &&
                friendRepository.findByRequestReceiverUsername(receiver.getUsername()).isPresent()){
            throw new RuntimeException("Friend already exists");
        }
        Friend friend = new Friend();
        friend.setRequestSender(creator);
        friend.setRequestReceiver(receiver);
        friend.setRequestDate(java.sql.Date.valueOf(LocalDate.now()));
        friend.setIsAccepted(false);
        return friendRepository.save(friend);
    }
}
