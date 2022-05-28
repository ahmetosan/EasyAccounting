package com.easyaccounting.entity;






import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
//@Table(name="roles")
@Where(clause = "is_deleted=false")

public class Role extends BaseEntity{

    private String description;

}
