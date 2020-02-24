package com.epam.lab.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class TagTo extends BaseTo {
    @NotNull
    @Size(min = 1, max = 30)
    private String name;

    public TagTo() {
    }

    public TagTo(Long id, String name) {
        super(id);
        this.name = name;
    }

    public TagTo(String name) {
        this(null, name);
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
