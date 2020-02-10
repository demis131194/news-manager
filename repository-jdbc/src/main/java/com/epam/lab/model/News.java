package com.epam.lab.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The type News.
 */
public class News extends BaseEntity {
    private String title;
    private String shortText;
    private String fullText;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;

    public News() {
        super(null);
    }

    public News(Long id, String title, String shortText, String fullText) {
        super(id);
        this.title = title;
        this.shortText = shortText;
        this.fullText = fullText;
    }

    public News(String title, String shortText, String fullText) {
        this(null, title, shortText, fullText);
    }

    public News(Long id, String title, String shortText, String fullText, LocalDateTime creationDate, LocalDateTime modificationDate) {
        this(id, title, shortText, fullText);
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }

    public News(String title, String shortText, String fullText, LocalDateTime creationDate, LocalDateTime modificationDate) {
        this(null, title, shortText, fullText, creationDate, modificationDate);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        News news = (News) o;
        return Objects.equals(title, news.title) &&
                Objects.equals(shortText, news.shortText) &&
                Objects.equals(fullText, news.fullText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, shortText, fullText);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("News{");
        sb.append("id='").append(this.getId()).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", shortTitle='").append(shortText).append('\'');
        sb.append(", fullText='").append(fullText).append('\'');
        sb.append(", creationDate=").append(creationDate);
        sb.append(", modificationDate=").append(modificationDate);
        sb.append('}');
        return sb.toString();
    }
}
