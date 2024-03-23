package org.koushik.javabrains.service;

import org.koushik.javabrains.database.DatabaseClass;
import org.koushik.javabrains.exception.DataNotFoundException;
import org.koushik.javabrains.model.Message;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MessageService {

    private static final Map<Long, Message> messages = DatabaseClass.getMessages();

    public MessageService() {
        messages.put(1L, new Message(1L, "Hello World", "koushik"));
        messages.put(2L, new Message(2L, "Hello Jersey", "koushik"));
    }

    public List<Message> getAllMessages() {
        return new ArrayList<>(messages.values());
    }

    public List<Message> getAllMessagesForYear(int year) {
        Calendar cal = Calendar.getInstance();
        return messages.values().stream()
                .filter(message -> {cal.setTime(message.getCreated()); return (cal.get(Calendar.YEAR) == year);})
                .collect(Collectors.toList());
    }

    public List<Message> getAllMessagesPaginated(int start, int size) {
        return new ArrayList<>(messages.values()).subList(start, start + size);
    }

    public Message getMessage(long id) {
        Message message = messages.get(id);

        if (message == null) {
            throw new DataNotFoundException("Message with id " + id + " not found");
        }

        return message;
    }

    public Message addMessage(Message message) {
//        message.setId(messages.size() + 1L);
        message.setId(messages.keySet().stream().reduce(Long::max).orElse(0L) + 1L);

        messages.put(message.getId(), message);

//        return messages.put(message.getId(), message);
        return getMessage(message.getId());
    }

    public Message updateMessage(Message message) {
        if (message.getId() <= 0) {
            return null;
        }

        messages.put(message.getId(), message);

//        return messages.put(message.getId(), message);
        return getMessage(message.getId());
    }

    public Message removeMessage(long id) {
        return messages.remove(id);
    }
}
