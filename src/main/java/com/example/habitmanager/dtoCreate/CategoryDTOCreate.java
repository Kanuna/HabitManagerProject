package com.example.habitmanager.dtoCreate;

import com.example.habitmanager.dto.CategoryDTO;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTOCreate extends CategoryDTO {
    private int category_id;
}