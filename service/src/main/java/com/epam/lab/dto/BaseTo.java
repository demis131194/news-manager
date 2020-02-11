package com.epam.lab.dto;

import com.epam.lab.annotation.BaseIdConstraint;

import java.util.Objects;

/**
 * The type Base dto.
 */
public abstract class BaseTo {

    @BaseIdConstraint
    private Long id;

    /**
     * Instantiates a new Base dto.
     *
     * @param id the id
     */
    public BaseTo(Long id) {
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
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
