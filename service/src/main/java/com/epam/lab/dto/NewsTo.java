package com.epam.lab.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class NewsTo extends BaseTo implements Serializable {

    private static final long serialVersionUID = 7360179076370646070L;

    @NotBlank
    @Size(max = 30)
    private String title;

    @NotBlank
    @Size(max = 200)
    private String shortText;

    @NotBlank
    @Size(max = 2000)
    private String fullText;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime creationDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime modificationDate;

    @Valid
    private AuthorTo author;

    @Valid
    private Set<@Valid TagTo> tags = new HashSet<>();

    public NewsTo() {
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

    public NewsTo(Long id, String title, String shortText, String fullText, AuthorTo author, Collection<TagTo> tags) {
        this(id, title, shortText, fullText);
        this.author = author;
        this.tags.addAll(tags);
    }

    public NewsTo(String title, String shortText, String fullText, AuthorTo author, Collection<TagTo> tags) {
        this(title, shortText, fullText);
        this.author = author;
        this.tags.addAll(tags);
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

    public Set<TagTo> getTags() {
        return tags;
    }

    public void setTags(Set<TagTo> tags) {
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
