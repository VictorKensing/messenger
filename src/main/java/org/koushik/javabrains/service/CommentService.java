package org.koushik.javabrains.service;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.koushik.javabrains.database.DatabaseClass;
import org.koushik.javabrains.model.Comment;
import org.koushik.javabrains.model.ErrorMessage;
import org.koushik.javabrains.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentService {

    private final Map<Long, Message> messages = DatabaseClass.getMessages();

    public List<Comment> getAllComments(long messageId) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();

        return new ArrayList<Comment>(comments.values());
    }

    public Comment getComment(long messageId, long commentId) {
        ErrorMessage errorMessage = new ErrorMessage(Response.Status.NOT_FOUND.getReasonPhrase(),
                Response.Status.NOT_FOUND.getStatusCode(), "https://github.com/koushik/java-brains");

        Response response = Response
                .status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();

        Message message = messages.get(messageId);

        if (message == null) {
            throw new WebApplicationException(response);
        }

        Map<Long, Comment> comments = message.getComments();

        Comment comment = comments.get(commentId);

        if (comment == null) {
            throw new NotFoundException(response);
        }

        return comment;
    }

    public Comment addComment(long messageId, Comment comment) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        comment.setId(comments.values().stream().map(Comment::getId).reduce(Long::max).orElse(0L) + 1L);
        comments.put(comment.getId(), comment);

        return comment;
    }

    public Comment updateComment(long messageId, Comment comment) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        if (comment.getId() <= 0) {
            return null;
        }
        comments.put(comment.getId(), comment);

        return comment;
    }

    public void removeComment(long messageId, long commentId) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        comments.remove(commentId);
    }
}
