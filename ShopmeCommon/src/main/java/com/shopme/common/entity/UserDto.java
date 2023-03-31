package com.shopme.common.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link User} entity
 */
public class UserDto implements Serializable {
    private final Integer id;
    private final String email;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String photos;
    private final boolean enabled;
    private final String photosImagePath;
    private final String fullName;

    public UserDto(Integer id, String email, String password, String firstName, String lastName, String photos, boolean enabled, String photosImagePath, String fullName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.photos = photos;
        this.enabled = enabled;
        this.photosImagePath = photosImagePath;
        this.fullName = fullName;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhotos() {
        return photos;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public String getPhotosImagePath() {
        return photosImagePath;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto entity = (UserDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.password, entity.password) &&
                Objects.equals(this.firstName, entity.firstName) &&
                Objects.equals(this.lastName, entity.lastName) &&
                Objects.equals(this.photos, entity.photos) &&
                Objects.equals(this.enabled, entity.enabled) &&
                Objects.equals(this.photosImagePath, entity.photosImagePath) &&
                Objects.equals(this.fullName, entity.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, firstName, lastName, photos, enabled, photosImagePath, fullName);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "email = " + email + ", " +
                "password = " + password + ", " +
                "firstName = " + firstName + ", " +
                "lastName = " + lastName + ", " +
                "photos = " + photos + ", " +
                "enabled = " + enabled + ", " +
                "photosImagePath = " + photosImagePath + ", " +
                "fullName = " + fullName + ")";
    }
}