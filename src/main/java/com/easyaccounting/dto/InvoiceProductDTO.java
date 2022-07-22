package com.easyaccounting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class InvoiceProductDTO {

    private Long id;
    private String name;
    private InvoiceDTO invoice;
    private ProductDTO product;
    private Long qty;
    private Long price;
    private Long tax;
    private Long profit;
}
