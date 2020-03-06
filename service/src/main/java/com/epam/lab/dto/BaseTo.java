package com.epam.lab.dto;

import com.epam.lab.dto.group.CreateGroup;
import com.epam.lab.dto.group.UpdateGroup;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.util.Objects;

public abstract class BaseTo {

    @NotNull(groups = UpdateGroup.class)
    @Positive(groups = UpdateGroup.class)
    @Null(groups = CreateGroup.class)
    private Long id;

    public BaseTo() {
    }

    public BaseTo(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseTo baseTo = (BaseTo) o;
        return Objects.equals(id, baseTo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BaseDto{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
