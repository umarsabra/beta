package com.marketapp.beta.Item;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/items")
public class ItemController {

    @Autowired
    ItemService itemService;

    @PostMapping
    Item postItem(@RequestBody Item item){
        System.out.println("omar");
        return itemService.addItem(item);
    }

    @GetMapping
    List<Item> getItems(){
        return itemService.getAllItems();
    }

}
