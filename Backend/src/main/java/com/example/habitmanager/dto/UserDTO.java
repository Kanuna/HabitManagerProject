package com.example.habitmanager.dto;
import com.example.habitmanager.models.Category;
import com.example.habitmanager.models.Habit;
import com.example.habitmanager.models.User;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class UserDTO {
    @NotBlank(message = "Firstname is required")
    private String firstname;
    @NotBlank(message = "Lastname is required")
    private String lastname;
    @NotBlank(message = "Age is required")
    private int age;
    @Email(message = "Email is required")
    private String email;
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
    @NotNull(message = "Role is required")
    private User.RoleEnum role;
  /*  @Nullable
    private List<HabitDTO> habits;
    @Nullable
    private List<CategoryDTO> categories;*/

    public void setFirstname(String firstname) {
        if(!firstname.isEmpty()){
            this.firstname = firstname;
        }
    }

    public void setLastname(String lastname) {
        if(!lastname.isEmpty()){
            this.lastname = lastname;
        }
    }

    public void setEmail(String email) {
        if(!email.isEmpty()){
            this.email = email;
        }
    }

    public void setPassword(String password) {
        if(!password.isEmpty()){
            this.password = password;
        }
    }
}
