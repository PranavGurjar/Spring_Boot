package com.validator.electronic.store.dto;

import com.validator.electronic.store.validate.ImageNameValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDto {

    private String userId;

    @Size(min = 3, max = 20, message = "Invalid Name!")
    private String name;

    @NotBlank(message = "Email is required")
    @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Invalid Email format!"
    )
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @Size(min = 4, max = 6, message = "Invalid Gender!")
    private String gender;

    @NotBlank(message = "Write something about yourself")
    private String about;

    //pattern
    //custom validator

    @ImageNameValid
    private String imageName;

    public UserDto() {
    }

    public UserDto(String userId, String name, String email, String password, String gender, String about, String imageName) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.about = about;
        this.imageName = imageName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public @Size(min = 3, max = 20, message = "Invalid Name!") String getName() {
        return name;
    }

    public void setName(@Size(min = 3, max = 20, message = "Invalid Name!") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Email is required") @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Invalid Email format!"
    ) String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required") @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Invalid Email format!"
    ) String email) {
        this.email = email;
    }

    public @NotBlank(message = "Password is required") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required") String password) {
        this.password = password;
    }

    public @Size(min = 4, max = 6, message = "Invalid Gender!") String getGender() {
        return gender;
    }

    public void setGender(@Size(min = 4, max = 6, message = "Invalid Gender!") String gender) {
        this.gender = gender;
    }

    public @NotBlank(message = "Write something about yourself") String getAbout() {
        return about;
    }

    public void setAbout(@NotBlank(message = "Write something about yourself") String about) {
        this.about = about;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }


}


