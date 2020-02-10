package com.epam.lab.model;

import java.util.Objects;

/**
 * The type Base entity.
 */
public abstract class BaseEntity {
    private Long id;

    /**
     * Instantiates a new Base entity.
     *
     * @param id the id
     */
    public BaseEntity(Long id) {
        this.id = id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
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
     * Is new boolean.
     *
     * @return the boolean
     */
    public boolean isNew() {
        return id==null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BaseEntity{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
