package org.example.service;

import org.example.entity.Note;
import org.example.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;

    public Note createNote(Note note, String username) {
        note.setOwnerUsername(username);
        return noteRepository.save(note);
    }

    public List<Note> getMyNotes(String username) {
        return noteRepository.findByOwnerUsername(username);
    }

    public List<Note> getAllNotesForAdmin() {
        return noteRepository.findAll();
    }
}