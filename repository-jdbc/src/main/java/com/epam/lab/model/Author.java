package com.epam.lab.model;


import java.util.Objects;

/**
 * The type Author.
 */
public class Author extends BaseEntity {
    private String name;
    private String surname;

    /**
     * Instantiates a new Author.
     */
    public Author() {
        super(null);
    }

    /**
     * Instantiates a new Author.
     *
     * @param id      the id
     * @param name    the name
     * @param surname the surname
     */
    public Author(Long id, String name, String surname) {
        super(id);
        this.name = name;
        this.surname = surname;
    }

    /**
     * Instantiates a new Author.
     *
     * @param name    the name
     * @param surname the surname
     */
    public Author(String name, String surname) {
        this(null, name, surname);
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
        Author author = (Author) o;
        return Objects.equals(name, author.name) &&
                Objects.equals(surname, author.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Author{");
        sb.append("id='").append(this.getId()).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
