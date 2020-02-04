package com.epam.lab.dto;

import java.util.Objects;

public class TagTo {
    private Long id;
    private String name;

    public TagTo() {
    }

    public TagTo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public TagTo(String name) {
        this(null, name);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TagTo tagTo = (TagTo) o;
        return Objects.equals(id, tagTo.id) &&
                Objects.equals(name, tagTo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TagTo{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
