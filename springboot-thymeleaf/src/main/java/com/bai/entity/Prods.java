package com.bai.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Prods {
    private String selfNumber;
    private String chartNo;
    private String company;
    private String invoiceNumber;
    private String unitPrice;
    private String totalPrice;
    private String remarks;
}