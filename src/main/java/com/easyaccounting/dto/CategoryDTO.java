package com.easyaccounting.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDTO {

    private Long id;
    private String description;
    private Boolean enabled;

    private CompanyDTO company;

    public CategoryDTO(String description, Boolean enabled) {
        this.description = description;
        this.enabled = enabled;
    }
}
