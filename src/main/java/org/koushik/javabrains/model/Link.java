package org.koushik.javabrains.model;

import java.util.Objects;

public class Link {

    private String url;
    private String rel;

    public Link() {
    }

    public Link(String url, String rel) {
        this.url = url;
        this.rel = rel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return Objects.equals(rel, link.rel);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(rel);
    }
}
