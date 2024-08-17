package com.example.itemservice.controller;

import com.example.itemservice.dto.ItemDTO;
import com.example.itemservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/{id}")
    public ItemDTO getItemById(@PathVariable String id) {
        return itemService.getItemById(id);
    }

    @GetMapping
    public List<ItemDTO> getAllItems() {
        return itemService.getAllItems();
    }

    @PostMapping
    public ItemDTO addItem(@RequestBody ItemDTO itemDTO) {
        return itemService.addItem(itemDTO);
    }

    @PutMapping("/{id}")
    public ItemDTO updateItemById(@PathVariable String id, @RequestBody ItemDTO itemDTO) {
        return itemService.updateItemById(id, itemDTO);
    }

    @DeleteMapping("/{upc}")
    public void deleteItemByUpc(@PathVariable String upc) {
        itemService.deleteItemByUpc(upc);
    }

    @DeleteMapping("/id/{id}")
    public void deleteItemById(@PathVariable String id) {
        itemService.deleteItemById(id);
    }
}
