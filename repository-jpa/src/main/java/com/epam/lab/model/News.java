package com.epam.lab.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.epam.lab.repository.DbConstants.*;

@Entity
@Table(name = NEWS_TABLE_NAME)
@Access(AccessType.FIELD)
@NamedQueries({
        @NamedQuery(name = News.DELETE, query = "DELETE FROM News n WHERE n.id=:id"),
        @NamedQuery(name = News.FIND_ALL, query = "SELECT n FROM News n"),
        @NamedQuery(name = News.COUNT_ALL, query = "SELECT COUNT(n) FROM News n")
})
public class News extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 662357221185464586L;

    public static final String DELETE = "News.delete";
    public static final String FIND_ALL = "News.findAll";
    public static final String COUNT_ALL = "News.countAll";

    @Column(name = NEWS_TITLE_COLUMN_NAME, nullable = false, length = 30)
    private String title;

    @Column(name = NEWS_SHORT_TEXT_COLUMN_NAME, nullable = false, length = 200)
    private String shortText;

    @Column(name = NEWS_FULL_TEXT_COLUMN_NAME, nullable = false, length = 2000)
    private String fullText;

    @Column(name = NEWS_CREATION_DATE_COLUMN_NAME, insertable = false, updatable = false)
    private LocalDateTime creationDate;

    @Column(name = NEWS_MODIFICATION_DATE_COLUMN_NAME, insertable = false, updatable = false)
    private LocalDateTime modificationDate;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @JoinTable(
            name = NEWS_AUTHORS_TABLE_NAME,
            joinColumns = @JoinColumn(name = NEWS_AUTHORS_NEWS_ID_COLUMN_NAME, referencedColumnName = ID_COLUMN_NAME),
            inverseJoinColumns = @JoinColumn(name = NEWS_AUTHORS_AUTHOR_ID_COLUMN_NAME, referencedColumnName = ID_COLUMN_NAME),
            foreignKey = @ForeignKey(name = NEWS_AUTHORS_NEWS_ID_COLUMN_NAME, foreignKeyDefinition = ID_COLUMN_NAME),
            inverseForeignKey = @ForeignKey(name = NEWS_AUTHORS_AUTHOR_ID_COLUMN_NAME, foreignKeyDefinition = ID_COLUMN_NAME),
            uniqueConstraints = @UniqueConstraint(columnNames = {NEWS_AUTHORS_NEWS_ID_COLUMN_NAME, NEWS_AUTHORS_AUTHOR_ID_COLUMN_NAME})
    )
    private Author author;

    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @JoinTable(
            name = NEWS_TAGS_TABLE_NAME,
            joinColumns = @JoinColumn(name = NEWS_TAGS_NEWS_ID_COLUMN_NAME, referencedColumnName = ID_COLUMN_NAME),
            inverseJoinColumns = @JoinColumn(name = NEWS_TAGS_TAG_ID_COLUMN_NAME, referencedColumnName = ID_COLUMN_NAME),
            foreignKey = @ForeignKey(name = NEWS_TAGS_NEWS_ID_COLUMN_NAME, foreignKeyDefinition = ID_COLUMN_NAME),
            inverseForeignKey = @ForeignKey(name = NEWS_TAGS_TAG_ID_COLUMN_NAME, foreignKeyDefinition = ID_COLUMN_NAME)
    )
    private Set<Tag> tags = new HashSet<>();

    public News() {
    }

    public News(Long id, String title, String shortText, String fullText, Author author, Collection<Tag> tags) {
        super(id);
        this.title = title;
        this.shortText = shortText;
        this.fullText = fullText;
        this.author = author;
        this.tags.addAll(tags);
    }

    public News(String title, String shortText, String fullText, Author author, Collection<Tag> tags) {
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        News news = (News) o;
        return Objects.equals(title, news.title) &&
                Objects.equals(shortText, news.shortText) &&
                Objects.equals(fullText, news.fullText) &&
                Objects.equals(author, news.author) &&
                Objects.equals(tags, news.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, shortText, fullText, author, tags);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("News{");
        sb.append("id='").append(this.getId()).append('\'');
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
