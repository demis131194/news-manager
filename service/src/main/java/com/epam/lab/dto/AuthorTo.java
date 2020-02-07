package com.epam.lab.dto;

import java.util.Comparator;
import java.util.Objects;

public class AuthorTo implements Comparable<AuthorTo> {
    private Long id;
    private String name;
    private String surname;

    public AuthorTo() {
    }

    public AuthorTo(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public int compareTo(AuthorTo authorTo) {
        return Comparator.comparing(AuthorTo::getSurname).thenComparing(AuthorTo::getName).compare(this, authorTo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorTo authorTo = (AuthorTo) o;
        return Objects.equals(id, authorTo.id) &&
                Objects.equals(name, authorTo.name) &&
                Objects.equals(surname, authorTo.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuthorTo{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
