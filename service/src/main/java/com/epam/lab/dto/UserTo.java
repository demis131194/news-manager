package com.epam.lab.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class UserTo extends BaseTo implements Serializable {

    private static final long serialVersionUID = 165608384289638734L;

    @NotBlank
    @Size(max = 20)
    private String name;

    @NotBlank
    @Size(max = 20)
    private String surname;

    @NotBlank
    @Size(max = 30)
    private String login;

    @NotBlank
    @Size(max = 30)
    private String password;

    private List< @Size(max = 30) String> roles;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserTo userTo = (UserTo) o;
        return Objects.equals(name, userTo.name) &&
                Objects.equals(surname, userTo.surname) &&
                Objects.equals(login, userTo.login) &&
                Objects.equals(roles, userTo.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname, login, roles);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserTo{");
        sb.append("name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", roles=").append(roles);
        sb.append('}');
        return sb.toString();
    }
}
