package com.anirudh.Noted.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anirudh.Noted.model.Notes;
import com.anirudh.Noted.model.User;
import com.anirudh.Noted.model.dto.Note;
import com.anirudh.Noted.repo.NoteRepo;
import com.anirudh.Noted.repo.UserRepo;

@Service
public class NoteService {

    @Autowired
    private NoteRepo noteRepo;

    @Autowired
    private UserRepo userRepo;

    public List<Notes> getAllNotes(String username) {
        User user = userRepo.findByUserName(username);
        return noteRepo.findAllByUserAndDeletedFalse(user);
    }

    public List<Notes> getAllDeletedNotes(String username) {
        User user = userRepo.findByUserName(username);
        return noteRepo.findAllByUserAndDeletedTrue(user);
    }
    
    public Notes getNoteById(String username, Long noteId) {
        User user = userRepo.findByUserName(username);
        return noteRepo.findBynoteIdAndUser(noteId, user).orElse(null);
    }

    public Notes createNote(String username, Note note) {
        User user = userRepo.findByUserName(username);
        Notes newNote = new Notes();
        newNote.setTitle(note.title());
        newNote.setContent(note.content());
        newNote.setUser(user);

        return noteRepo.save(newNote);
    }

    public Notes deleteNote(String username, Long noteId) {
    
        User user = userRepo.findByUserName(username);
        Notes note = noteRepo.findBynoteIdAndUser(noteId, user).orElse(null);

        if (note != null)
            note.setDeleted(true);
            note.setDeletedOn(LocalDateTime.now());
            noteRepo.save(note);

        return note;

    }

    public Notes restoreNote(String username, Long noteId) {
User user = userRepo.findByUserName(username);
        Notes note = noteRepo.findBynoteIdAndUser(noteId, user).orElse(null);

        if (note != null)
            note.setDeleted(false);
            note.setDeletedOn(null);
            noteRepo.save(note);

        return note;
    }

    public List<Notes> searchNotes(String username, String keyword) {

        User user = userRepo.findByUserName(username);
        return noteRepo.searchNotes(user, keyword);
        
    }

}
