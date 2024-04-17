package com.suaimori.backend.controllers;

import com.suaimori.backend.helpers.JsonConverterHelper;
import com.suaimori.backend.model.dto.UserDTO;
import com.suaimori.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username, @RequestParam List<String> fields) {
        var user = userService.getUser(username, fields);
        return ResponseEntity.ok(JsonConverterHelper.convertTupleListToJson(user));
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody UserDTO userDTO) {
        userService.updateUser(username, userDTO);
        return ResponseEntity.ok().build();
    }
}
