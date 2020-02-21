package com.epam.lab.model;

import com.epam.lab.repository.DbConstants;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = DbConstants.TAGS_TABLE_NAME)
@Access(AccessType.FIELD)
@NamedQueries({
        @NamedQuery(name = Tag.COUNT_ALL, query = "SELECT COUNT(t) FROM Tag t"),
        @NamedQuery(name = Tag.DELETE, query = "DELETE FROM Tag t WHERE t.id=:id"),
        @NamedQuery(name = Tag.FIND_ALL, query = "SELECT t FROM Tag t")                     // FIXME: 2/19/2020 Refactor??
})
public class Tag extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 4998934308271340034L;

    public static final String COUNT_ALL = "User.countAll";
    public static final String DELETE = "User.delete";
    public static final String FIND_ALL = "User.findAll";

    @NotBlank
    @Length(max = 30)
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
