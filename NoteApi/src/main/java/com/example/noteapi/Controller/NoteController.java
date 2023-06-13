package com.example.noteapi.Controller;

import com.example.noteapi.Client.UserServiceClient;
import com.example.noteapi.DTO.NoteDTO;
import com.example.noteapi.Entity.Note;
import com.example.noteapi.Entity.User;
import com.example.noteapi.Service.NoteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RestControllerAdvice
@CrossOrigin(origins = { "http://127.0.0.1:5173", "http://localhost:5173", "http://192.168.0.183:5173" })
@Validated
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;
    private final UserServiceClient userServiceClient;

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody NoteDTO noteDTO) {
        Optional<User> user = Optional.ofNullable(userServiceClient.searchById(noteDTO.getUser_id()));
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User id " + noteDTO.getUser_id() + " do not exists");
        } else {
            noteService.add(noteDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Note created");
        }
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> listByUser(@PathVariable Long id) {
        List<Note> notes = noteService.listByUserId(id);
        var array = new ArrayList<>();

        return notes.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(array)
                : ResponseEntity.ok(notes);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Note> note = noteService.findById(id);
        if (note.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note with ID " + id + " not found");
        } else {
            noteService.delete(id);
            return ResponseEntity.ok().body("Note deleted succesfully");
        }
    }

    @GetMapping("/search/{id}/{string}")
    public ResponseEntity<?> searchNote(@PathVariable Long id,@PathVariable String string) {
        List<Note> notes = noteService.searchNote(id,string);
        var array = new ArrayList<>();
        return notes.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(array)
                : ResponseEntity.ok(notes);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@RequestBody Note note) {
        Optional<User> user = Optional.ofNullable(userServiceClient.searchById(note.getUser_id()));
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User id " + note.getUser_id() + " do not exists");
        } else {
            noteService.update(note);
            return ResponseEntity.status(HttpStatus.CREATED).body("Note updated");
        }
    }

}
