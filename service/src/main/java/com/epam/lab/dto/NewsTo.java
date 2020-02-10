package com.epam.lab.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type News to.
 */
public class NewsTo extends BaseDto{
    @NotNull
    @Size(min = 1, max = 30)
    private String title;

    @NotNull
    @Size(min = 1, max = 200)
    private String shortText;

    @NotNull
    @Size(min = 1, max = 2000)
    private String fullText;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime creationDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime modificationDate;

    @Valid
    private AuthorTo author;

    @Valid
    private List<@Valid TagTo> tags = new ArrayList<>();

    /**
     * Instantiates a new News to.
     */
    public NewsTo() {
        super(null);
    }

    /**
     * Instantiates a new News to.
     *
     * @param id        the id
     * @param title     the title
     * @param shortText the short text
     * @param fullText  the full text
     */
    public NewsTo(Long id, String title, String shortText, String fullText) {
        super(id);
        this.title = title;
        this.shortText = shortText;
        this.fullText = fullText;
    }

    /**
     * Instantiates a new News to.
     *
     * @param title     the title
     * @param shortText the short text
     * @param fullText  the full text
     */
    public NewsTo(String title, String shortText, String fullText) {
        this(null, title, shortText, fullText);
    }

    /**
     * Instantiates a new News to.
     *
     * @param id               the id
     * @param title            the title
     * @param shortText        the short text
     * @param fullText         the full text
     * @param creationDate     the creation date
     * @param modificationDate the modification date
     */
    public NewsTo(Long id, String title, String shortText, String fullText, LocalDateTime creationDate, LocalDateTime modificationDate) {
        this(id, title, shortText, fullText);
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }

    /**
     * Instantiates a new News to.
     *
     * @param title            the title
     * @param shortText        the short text
     * @param fullText         the full text
     * @param creationDate     the creation date
     * @param modificationDate the modification date
     */
    public NewsTo(String title, String shortText, String fullText, LocalDateTime creationDate, LocalDateTime modificationDate) {
        this(null, title, shortText, fullText, creationDate, modificationDate);
    }

    /**
     * Instantiates a new News to.
     *
     * @param id        the id
     * @param title     the title
     * @param shortText the short text
     * @param fullText  the full text
     * @param author    the author
     * @param tags      the tags
     */
    public NewsTo(Long id, String title, String shortText, String fullText, AuthorTo author, List<TagTo> tags) {
        this(id, title, shortText, fullText);
        this.author = author;
        this.tags = tags;
    }

    /**
     * Instantiates a new News to.
     *
     * @param title     the title
     * @param shortText the short text
     * @param fullText  the full text
     * @param author    the author
     * @param tags      the tags
     */
    public NewsTo(String title, String shortText, String fullText, AuthorTo author, List<TagTo> tags) {
        this(null, title, shortText, fullText, author, tags);
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets short text.
     *
     * @return the short text
     */
    public String getShortText() {
        return shortText;
    }

    /**
     * Sets short text.
     *
     * @param shortText the short text
     */
    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    /**
     * Gets full text.
     *
     * @return the full text
     */
    public String getFullText() {
        return fullText;
    }

    /**
     * Sets full text.
     *
     * @param fullText the full text
     */
    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    /**
     * Gets creation date.
     *
     * @return the creation date
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Sets creation date.
     *
     * @param creationDate the creation date
     */
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Gets modification date.
     *
     * @return the modification date
     */
    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    /**
     * Sets modification date.
     *
     * @param modificationDate the modification date
     */
    public void setModificationDate(LocalDateTime modificationDate) {
        this.modificationDate = modificationDate;
    }

    /**
     * Gets author.
     *
     * @return the author
     */
    public AuthorTo getAuthor() {
        return author;
    }

    /**
     * Sets author.
     *
     * @param author the author
     */
    public void setAuthor(AuthorTo author) {
        this.author = author;
    }

    /**
     * Gets tags.
     *
     * @return the tags
     */
    public List<TagTo> getTags() {
        return tags;
    }

    /**
     * Sets tags.
     *
     * @param tags the tags
     */
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
