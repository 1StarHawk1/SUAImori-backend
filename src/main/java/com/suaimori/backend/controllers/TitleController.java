package com.suaimori.backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suaimori.backend.model.dto.TitleDTO;
import com.suaimori.backend.services.TitleService;
import jakarta.persistence.Tuple;
import jakarta.persistence.TupleElement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/title")
@RequiredArgsConstructor
public class TitleController {

    private final TitleService titleService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getTitle(@PathVariable Long id, @RequestParam List<String> fields) {
        var title = titleService.getTitle(id, fields);
        return ResponseEntity.ok(convertTupleListToJson(title));
    }

    @GetMapping("/getallids")
    public ResponseEntity<?> getTitles() {
        var idOfAll = titleService.getAllId();
        return ResponseEntity.ok(idOfAll);
    }

    @GetMapping("/getallanimeids")
    public ResponseEntity<?> getAnimeTitles() {
        var idOfAll = titleService.getAllAnimeId();
        return ResponseEntity.ok(idOfAll);
    }

    @GetMapping("/getallmangaids")
    public ResponseEntity<?> getMangaTitles() {
        var idOfAll = titleService.getAllMangaId();
        return ResponseEntity.ok(idOfAll);
    }

    @GetMapping("/getongoinganimeids")
    public ResponseEntity<?> getOngoingAnimeTitles() {
        var idOfAll = titleService.getOngoingAnimeId();
        return ResponseEntity.ok(idOfAll);
    }

    public String convertTupleListToJson(List<Tuple> tuples) {
    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> jsonMap = new HashMap<>();

    for (Tuple tuple : tuples) {
        for (TupleElement<?> element : tuple.getElements()) {
            jsonMap.put(element.getAlias(), tuple.get(element.getAlias()));
        }
    }

    String json = "";
    try {
        json = mapper.writeValueAsString(jsonMap);
    } catch (Exception e) {
        e.printStackTrace();
    }

    return json;
}

    @PostMapping("")
    public ResponseEntity<?> createTitle(@RequestBody TitleDTO titleDTO) throws ChangeSetPersister.NotFoundException {
        titleService.create(titleDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTitle(@PathVariable Long id) {
        titleService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTitle(@PathVariable Long id, @RequestBody TitleDTO titleDTO) throws ChangeSetPersister.NotFoundException {
        titleService.update(id, titleDTO);
        return ResponseEntity.ok().build();
    }
}
