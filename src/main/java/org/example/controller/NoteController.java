package org.example.controller;

import org.example.dto.APIResponse;
import org.example.entity.Note;
import org.example.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/notes")
    public ResponseEntity<APIResponse<Note>> createNote(@RequestBody Note note, Authentication auth) {
        Note savedNote = noteService.createNote(note, auth.getName());
        return ResponseEntity.ok(new APIResponse<>(true, "Note created", savedNote));
    }

    @GetMapping("/notes")
    public ResponseEntity<APIResponse<List<Note>>> getMyNotes(Authentication auth) {
        return ResponseEntity.ok(new APIResponse<>(true, "Success", noteService.getMyNotes(auth.getName())));
    }

    @GetMapping("/admin/notes")
    public ResponseEntity<APIResponse<List<Note>>> getAllNotes() {
        return ResponseEntity.ok(new APIResponse<>(true, "Admin Access", noteService.getAllNotesForAdmin()));
    }
}