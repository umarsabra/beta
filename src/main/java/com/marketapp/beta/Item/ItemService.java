package com.marketapp.beta.Item;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;
    Item addItem(Item item){
       return itemRepository.save(item);
    }
    List<Item> getAllItems(){
        return itemRepository.findAll();
    }
}
