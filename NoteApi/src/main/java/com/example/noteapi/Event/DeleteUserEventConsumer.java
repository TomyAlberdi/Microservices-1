package com.example.noteapi.Event;

import com.example.noteapi.Configuration.RabbitMQConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DeleteUserEventConsumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_USER_DELETED)
    public void listen(DeleteUserEventConsumer.Data message) {
        System.out.println("Id User Deleted: " + message.user_id);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Data {
        private Long user_id;
    }

}
