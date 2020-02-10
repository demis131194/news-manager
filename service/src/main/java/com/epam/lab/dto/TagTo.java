package com.epam.lab.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * The type Tag to.
 */
public class TagTo extends BaseDto{
    @NotNull
    @Size(min = 1, max = 30)
    private String name;

    /**
     * Instantiates a new Tag to.
     */
    public TagTo() {
        super(null);
    }

    /**
     * Instantiates a new Tag to.
     *
     * @param id   the id
     * @param name the name
     */
    public TagTo(Long id, String name) {
        super(id);
        this.name = name;
    }

    /**
     * Instantiates a new Tag to.
     *
     * @param name the name
     */
    public TagTo(String name) {
        this(null, name);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TagTo tagTo = (TagTo) o;
        return Objects.equals(name, tagTo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TagTo{");
        sb.append("id=").append(this.getId());
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
