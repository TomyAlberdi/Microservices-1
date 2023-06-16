package com.example.noteapi.Service;

import com.example.noteapi.Client.UserServiceClient;
import com.example.noteapi.DTO.NoteDTO;
import com.example.noteapi.Entity.Note;
import com.example.noteapi.DTO.UserDTO;
import com.example.noteapi.Repository.NoteRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
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

        Optional<UserDTO> userOptional = Optional.ofNullable(findUser(noteDTO.getUser_id()));
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        } else {
            note.setUser_id(noteDTO.getUser_id());
            return noteRepository.save(note);
        }
    }

    @Retry(name = "retryNote")
    @CircuitBreaker(name = "clientNote", fallbackMethod = "findUserFallback")
    private UserDTO findUser(Long id) {
        return userServiceClient.searchById(id);
    }

    public UserDTO findUserFallback(Long id, Throwable t) throws Exception {
        throw new Exception("User Not Found");
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
