package com.epam.lab.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

public class AuthorTo extends BaseTo implements Serializable {

    private static final long serialVersionUID = 8104859493654844490L;

    @NotBlank
    @Size(max = 30)
    private String name;

    @NotBlank
    @Size(max = 30)
    private String surname;

    public AuthorTo() {
    }

    public AuthorTo(Long id, String name, String surname) {
        super(id);
        this.name = name;
        this.surname = surname;
    }

    public AuthorTo(String name, String surname) {
        this(null, name, surname);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AuthorTo authorTo = (AuthorTo) o;
        return Objects.equals(name, authorTo.name) &&
                Objects.equals(surname, authorTo.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuthorTo{");
        sb.append("id='").append(this.getId()).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
