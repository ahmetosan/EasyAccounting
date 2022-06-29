package com.easyaccounting.dto;

import com.easyaccounting.enums.ProductStatus;
import com.easyaccounting.enums.UnitsType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {


    private Long id ;
    private String companyName;
    private String name;
    private String description;
    private int  quantity;
    private String unit;
    private int lowLimitAlert;
    private Boolean enabled;
    private int createdBy;
    private CategoryDTO category;
    @Enumerated(EnumType.STRING)
    private UnitsType unitsType;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdTime;
    private int updatedBy;
    private CompanyDTO company;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate updatedTime;


    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
    private Boolean isDeleted;

}
