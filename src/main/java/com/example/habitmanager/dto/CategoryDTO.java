package com.example.habitmanager.dto;

import com.example.habitmanager.models.Habit;
import com.example.habitmanager.models.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDTO {
    @NotBlank(message = "Name is required")
    public String name;
    @NotNull(message = "Color code is required")
    public String colorCode;
    private List<Habit> habits;
    @NotNull
    private User user;


    public void setName(String name) {
        if(!name.isEmpty()){
            this.name = name;
        }
    }

    public void setColorCode(String colorCode) {
        if(!colorCode.isEmpty()){
            this.colorCode = colorCode;
        }
    }
}
