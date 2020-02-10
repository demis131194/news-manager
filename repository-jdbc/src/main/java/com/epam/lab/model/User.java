package com.epam.lab.model;

import java.util.Objects;

/**
 * The type User.
 */
public class User extends BaseEntity {
    private String name;
    private String surname;
    private String login;
    private String password;

    /**
     * Instantiates a new User.
     *
     * @param id       the id
     * @param name     the name
     * @param surname  the surname
     * @param login    the login
     * @param password the password
     */
    public User(Long id, String name, String surname, String login, String password) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }

    /**
     * Instantiates a new User.
     *
     * @param name     the name
     * @param surname  the surname
     * @param login    the login
     * @param password the password
     */
    public User(String name, String surname, String login, String password) {
        super(null);
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
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

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname, login);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id='").append(this.getId()).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
