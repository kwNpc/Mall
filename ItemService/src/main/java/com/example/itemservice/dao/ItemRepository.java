package com.example.itemservice.dao;

import com.example.itemservice.entity.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {
    Item findByUpc(String upc);
}
