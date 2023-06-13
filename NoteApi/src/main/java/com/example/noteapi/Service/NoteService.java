package com.example.noteapi.Service;

import com.example.noteapi.Client.UserServiceClient;
import com.example.noteapi.DTO.NoteDTO;
import com.example.noteapi.Entity.Note;
import com.example.noteapi.Entity.User;
import com.example.noteapi.Repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserServiceClient userServiceClient;

    public Note add(NoteDTO noteDTO) {

        Note note = new Note();
        note.setText(noteDTO.getText());
        note.setTitle(noteDTO.getTitle());

        Optional<User> userOptional = Optional.ofNullable(userServiceClient.searchById(noteDTO.getUser_id()));
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        } else {
            note.setUser_id(noteDTO.getUser_id());
            return noteRepository.save(note);
        }
    }

    public List<Note> listByUserId(Long id) {
        return noteRepository.getByUserId(id);
    }

    public Optional<Note> findById(Long id) {
        return noteRepository.findById(id);
    }

    public void delete(Long id) {
        noteRepository.deleteById(id);
    }

    public List<Note> searchNote(Long id, String string) {
        return noteRepository.searchNote(id, string);
    }

    public Note update(Note note) {
        return noteRepository.save(note);
    }

}
