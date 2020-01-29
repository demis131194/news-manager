package com.epam.lab.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class News extends BaseEntity {
    private String title;
    private String shortTitle;
    private String fullText;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;

    public News(Long id, String title, String shortTitle, String fullText, LocalDateTime creationDate) {
        super(id);
        this.title = title;
        this.shortTitle = shortTitle;
        this.fullText = fullText;
        this.creationDate = creationDate;
        this.modificationDate = creationDate;
    }

    public News(String title, String shortTitle, String fullText, LocalDateTime creationDate) {
        super(null);
        this.title = title;
        this.shortTitle = shortTitle;
        this.fullText = fullText;
        this.creationDate = creationDate;
        this.modificationDate = creationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
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
                Objects.equals(shortTitle, news.shortTitle) &&
                Objects.equals(fullText, news.fullText) &&
                Objects.equals(creationDate, news.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, shortTitle, fullText, creationDate);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("News{");
        sb.append("id='").append(this.getId()).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", shortTitle='").append(shortTitle).append('\'');
        sb.append(", fullText='").append(fullText).append('\'');
        sb.append(", creationDate=").append(creationDate);
        sb.append(", modificationDate=").append(modificationDate);
        sb.append('}');
        return sb.toString();
    }
}
