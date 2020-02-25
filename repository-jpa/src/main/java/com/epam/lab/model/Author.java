package com.epam.lab.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import static com.epam.lab.repository.DbConstants.*;

@Entity
@Table(name = AUTHORS_TABLE_NAME)
@Access(AccessType.FIELD)
@NamedQueries({
        @NamedQuery(name = Author.DELETE, query = "DELETE FROM Author a WHERE a.id=:id"),
        @NamedQuery(name = Author.FIND_ALL, query = "SELECT a FROM Author a"),
        @NamedQuery(name = Author.COUNT_ALL, query = "SELECT COUNT(a) FROM Author a")
})
public class Author extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1598518282955545320L;

    public static final String DELETE = "Author.delete";
    public static final String FIND_ALL = "Author.findAll";
    public static final String COUNT_ALL = "Author.countAll";

    @Column(name = AUTHORS_NAME_COLUMN_NAME, nullable = false)
    private String name;

    @Column(name = AUTHORS_SURNAME_COLUMN_NAME, nullable = false)
    private String surname;

    public Author() {
    }

    public Author(Long id, String name, String surname) {
        super(id);
        this.name = name;
        this.surname = surname;
    }

    public Author(String name, String surname) {
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
