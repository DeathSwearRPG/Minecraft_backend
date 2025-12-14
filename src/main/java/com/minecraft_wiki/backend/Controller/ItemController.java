package com.minecraft_wiki.backend.Controller;

import com.minecraft_wiki.backend.Model.Item;
import com.minecraft_wiki.backend.Model.enums.ItemRarity;
import com.minecraft_wiki.backend.Model.enums.ItemType;
import com.minecraft_wiki.backend.Model.enums.SpecialCharacteristic;
import com.minecraft_wiki.backend.Service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getAllItems(@RequestParam(required = false) ItemType type,
                                  @RequestParam(required = false) ItemRarity rarity,
                                  @RequestParam(required = false) SpecialCharacteristic special) {
        return itemService.getItems(type,rarity,special);
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable("id") UUID itemId) {
        return itemService.getItemById(itemId);
    }

}
