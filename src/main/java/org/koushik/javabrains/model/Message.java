package org.koushik.javabrains.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@XmlRootElement
public class Message {

    private Long id;
    private String message;
    private Date created;
    private String author;
    private final Map<Long, Comment> comments = new HashMap<>();
    private final Set<Link> links = new HashSet<>();

    public Message() {
    }

    public Message(Long id, String message, String author) {
        this.id = id;
        this.message = message;
        this.author = author;
        this.created = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @XmlTransient
    public Map<Long, Comment> getComments() {
        return comments;
    }

    public Set<Link> getLinks() {
        return links;
    }

    public void addLink(String url, String rel) {
        Link link = new Link(url, rel);
        this.links.add(link);
    }
}
