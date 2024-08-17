package com.example.itemservice.dto;

import lombok.Data;

@Data
public class ItemDTO {
    private String itemName;
    private String upc;
    private double unitPrice;
    private int availableUnits;
    private String itemPictureUrl;
    // 其他必要的字段
}
