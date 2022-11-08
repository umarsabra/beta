package com.marketapp.beta.Item;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;
    public Item addItem(Item item){
       return itemRepository.save(item);
    }
    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    public Item getItemByBarcode(Long barcode) {
        return itemRepository.findItemByBarcode(barcode);
    }
}
