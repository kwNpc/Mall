package com.example.itemservice.service;

import com.example.itemservice.dao.ItemRepository;
import com.example.itemservice.dto.ItemDTO;
import com.example.itemservice.entity.Item;
import com.example.itemservice.exception.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    private ItemDTO convertToDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItemName(item.getItemName());
        itemDTO.setUpc(item.getUpc());
        itemDTO.setUnitPrice(item.getUnitPrice());
        itemDTO.setAvailableUnits(item.getAvailableUnits());
        itemDTO.setItemPictureUrl(item.getItemPictureUrl());
        return itemDTO;
    }

    private Item convertToEntity(ItemDTO itemDTO) {
        Item item = new Item();
        item.setItemName(itemDTO.getItemName());
        item.setUpc(itemDTO.getUpc());
        item.setUnitPrice(itemDTO.getUnitPrice());
        item.setAvailableUnits(itemDTO.getAvailableUnits());
        item.setItemPictureUrl(itemDTO.getItemPictureUrl());
        return item;
    }

    public ItemDTO getItemByUpc(String upc) {
        Item item = itemRepository.findByUpc(upc);
        if (item != null) {
            return convertToDTO(item);
        } else {
            throw new ItemNotFoundException("Item with UPC " + upc + " not found");
        }
    }

    public ItemDTO getItemById(String id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            return convertToDTO(item.get());
        } else {
            throw new ItemNotFoundException("Item with ID " + id + " not found");
        }
    }

    public ItemDTO addItem(ItemDTO itemDTO) {
        Item item = convertToEntity(itemDTO);
        Item savedItem = itemRepository.save(item);
        return convertToDTO(savedItem);
    }

    public ItemDTO updateItemById(String id, ItemDTO itemDTO) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            item.setItemName(itemDTO.getItemName());
            item.setUpc(itemDTO.getUpc());
            item.setUnitPrice(itemDTO.getUnitPrice());
            item.setAvailableUnits(itemDTO.getAvailableUnits());
            item.setItemPictureUrl(itemDTO.getItemPictureUrl());
            Item updatedItem = itemRepository.save(item);
            return convertToDTO(updatedItem);
        } else {
            throw new ItemNotFoundException("Item with ID " + id + " not found");
        }
    }

    public void deleteItemByUpc(String upc) {
        Item item = itemRepository.findByUpc(upc);
        if (item != null) {
            itemRepository.delete(item);
        } else {
            throw new ItemNotFoundException("Item with UPC " + upc + " not found");
        }
    }

    public void deleteItemById(String id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
        } else {
            throw new ItemNotFoundException("Item with ID " + id + " not found");
        }
    }

    public List<ItemDTO> getAllItems() {
        return itemRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
