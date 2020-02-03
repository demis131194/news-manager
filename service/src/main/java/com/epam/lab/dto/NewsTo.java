package com.epam.lab.dto;

import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.model.Tag;

import java.util.Objects;
import java.util.Set;

public class NewsTo {
    private News news;
    private Author author;
    private Set<Tag> tags;

    public NewsTo(News news, Author author, Set<Tag> tags) {
        Objects.requireNonNull(news);
        Objects.requireNonNull(author);
        Objects.requireNonNull(tags);
        this.news = news;
        this.author = author;
        this.tags = tags;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
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
        NewsTo newsTo = (NewsTo) o;
        return Objects.equals(author, newsTo.author) &&
                Objects.equals(tags, newsTo.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author, tags);
    }
}
