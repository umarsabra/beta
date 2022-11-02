package com.marketapp.beta.Item;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;
    Item addItem(Item item){
       return itemRepository.save(item);
    }
}
