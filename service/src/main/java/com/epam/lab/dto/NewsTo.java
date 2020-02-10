package com.epam.lab.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NewsTo extends BaseDto{
    private String title;
    private String shortText;
    private String fullText;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime creationDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime modificationDate;

    private AuthorTo author;
    private List<TagTo> tags = new ArrayList<>();

    public NewsTo() {
        super(null);
    }

    public NewsTo(Long id, String title, String shortText, String fullText) {
        super(id);
        this.title = title;
        this.shortText = shortText;
        this.fullText = fullText;
    }

    public NewsTo(String title, String shortText, String fullText) {
        this(null, title, shortText, fullText);
    }

    public NewsTo(Long id, String title, String shortText, String fullText, LocalDateTime creationDate, LocalDateTime modificationDate) {
        this(id, title, shortText, fullText);
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }

    public NewsTo(String title, String shortText, String fullText, LocalDateTime creationDate, LocalDateTime modificationDate) {
        this(null, title, shortText, fullText, creationDate, modificationDate);
    }

    public NewsTo(Long id, String title, String shortText, String fullText, AuthorTo author, List<TagTo> tags) {
        this(id, title, shortText, fullText);
        this.author = author;
        this.tags = tags;
    }

    public NewsTo(String title, String shortText, String fullText, AuthorTo author, List<TagTo> tags) {
        this(null, title, shortText, fullText, author, tags);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDateTime modificationDate) {
        this.modificationDate = modificationDate;
    }

    public AuthorTo getAuthor() {
        return author;
    }

    public void setAuthor(AuthorTo author) {
        this.author = author;
    }

    public List<TagTo> getTags() {
        return tags;
    }

    public void setTags(List<TagTo> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        NewsTo newsTo = (NewsTo) o;
        return Objects.equals(title, newsTo.title) &&
                Objects.equals(shortText, newsTo.shortText) &&
                Objects.equals(fullText, newsTo.fullText) &&
                Objects.equals(author, newsTo.author) &&
                Objects.equals(tags, newsTo.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, shortText, fullText, author, tags);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NewsTo{");
        sb.append("id=").append(this.getId());
        sb.append(", title='").append(title).append('\'');
        sb.append(", shortText='").append(shortText).append('\'');
        sb.append(", fullText='").append(fullText).append('\'');
        sb.append(", creationDate=").append(creationDate);
        sb.append(", modificationDate=").append(modificationDate);
        sb.append(", author=").append(author);
        sb.append(", tags=").append(tags);
        sb.append('}');
        return sb.toString();
    }
}
