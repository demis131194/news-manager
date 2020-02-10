package com.epam.lab.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * The type Author to.
 */
public class AuthorTo extends BaseDto {
    @NotNull
    @Size(min = 1, max = 30)
    private String name;

    @NotNull
    @Size(min = 1, max = 30)
    private String surname;

    /**
     * Instantiates a new Author to.
     */
    public AuthorTo() {
        super(null);
    }

    /**
     * Instantiates a new Author to.
     *
     * @param id      the id
     * @param name    the name
     * @param surname the surname
     */
    public AuthorTo(Long id, String name, String surname) {
        super(id);
        this.name = name;
        this.surname = surname;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets surname.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets surname.
     *
     * @param surname the surname
     */
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
