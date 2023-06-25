package com.example.noteapi.Event;

import com.example.noteapi.Configuration.RabbitMQConfig;
import com.example.noteapi.Repository.NoteRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DeleteUserEventConsumer {

    private final NoteRepository noteRepository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_USER_DELETED)
    public void listen(DeleteUserEventConsumer.Data message) {
        System.out.println("Notes with UserID " + message.user_id + " deleted.");
        noteRepository.deleteAllByUser(message.user_id);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Data {
        private Long user_id;
    }

}
