package com.epam.lab.model;

import com.epam.lab.repository.DbConstants;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = DbConstants.TAGS_TABLE_NAME)
@Access(AccessType.FIELD)
@NamedQueries({
        @NamedQuery(name = Tag.COUNT_ALL, query = "SELECT COUNT(t) FROM Tag t"),
        @NamedQuery(name = Tag.DELETE, query = "DELETE FROM Tag t WHERE t.id=:id"),
        @NamedQuery(name = Tag.FIND_ALL, query = "SELECT t FROM Tag t"),
        @NamedQuery(name = Tag.FIND_BY_NAME, query = "SELECT t FROM Tag t WHERE t.name=?1")
})
public class Tag extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 4998934308271340034L;

    public static final String COUNT_ALL = "Tag.countAll";
    public static final String DELETE = "Tag.delete";
    public static final String FIND_ALL = "Tag.findAll";
    public static final String FIND_BY_NAME = "Tag.findByName";

    @Column(name = DbConstants.TAGS_NAME_COLUMN_NAME, nullable = false, unique = true)
    private String name;

    public Tag() {
    }

    public Tag(Long id, String name) {
        super(id);
        this.name = name;
    }

    public Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(name, tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tag{");
        sb.append("id='").append(this.getId()).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
