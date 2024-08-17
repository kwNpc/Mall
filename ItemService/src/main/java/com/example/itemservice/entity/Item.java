package com.example.itemservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.UUID;

@Data
@Document(collection = "items")
public class Item {
    @Id
    private String id = UUID.randomUUID().toString();
    private String itemName;
    private String upc;
    private double unitPrice;
    private int availableUnits;  // 库存数量
    private String itemPictureUrl;
    // 其他必要的字段
}
