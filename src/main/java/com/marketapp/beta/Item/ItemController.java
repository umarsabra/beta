package com.marketapp.beta.Item;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
