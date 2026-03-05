package com.anirudh.Noted.controller;

import com.anirudh.Noted.model.Notes;
import com.anirudh.Noted.service.NoteService;
import com.anirudh.Noted.model.dto.Note;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;


    @GetMapping("/notes")
    public ResponseEntity<List<Notes>> getAllNotes(@AuthenticationPrincipal UserDetails user) {
        List<Notes> notes = noteService.getAllNotes(user.getUsername());
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @GetMapping("/bin")
    public ResponseEntity<List<Notes>> getAllDeletedNotes(@AuthenticationPrincipal UserDetails user) {
        List<Notes> notes = noteService.getAllDeletedNotes(user.getUsername());
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<Notes> getNote(@AuthenticationPrincipal UserDetails user, @PathVariable("noteId") Long noteId) {
        String username = user.getUsername();
        Notes note = noteService.getNoteById(username, noteId);
        if (note != null)
            return new ResponseEntity<>(note, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<Notes> createNote(@AuthenticationPrincipal UserDetails user, @RequestBody Note note) {
        String username = user.getUsername();
        Notes savedNote = noteService.createNote(username, note);
        return new ResponseEntity<>(savedNote, HttpStatus.OK);
    }

    @PostMapping("/{noteId}")
    public ResponseEntity<Notes> deleteNote(@AuthenticationPrincipal UserDetails user, @PathVariable("noteId") Long noteId) {
        String username = user.getUsername();
        Notes deletedNote = noteService.deleteNote(username, noteId);
        if (deletedNote != null)
            return new ResponseEntity<>(deletedNote, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/restore/{noteId}")
    public ResponseEntity<Notes> restoreNote(@AuthenticationPrincipal UserDetails user, @PathVariable("noteId") Long noteId) {
        String username = user.getUsername();
        Notes restoredNote = noteService.restoreNote(username, noteId);
        if (restoredNote != null)
            return new ResponseEntity<>(restoredNote, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    

    @GetMapping("/search") 
    public ResponseEntity<List<Notes>> searchProducts(@AuthenticationPrincipal UserDetails user, @RequestParam String keyword) {
        String username = user.getUsername();
        List<Notes> notes = noteService.searchNotes(username, keyword);
        System.out.println("searching for: " + keyword);
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

} 
