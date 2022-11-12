package com.marketapp.beta.Item;


import com.marketapp.beta.Exception.ItemAlreadyExistsException;
import com.marketapp.beta.OrderItem.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service

public class ItemService {

    @Autowired
    ItemRepository itemRepository;
    public Item addItem(Item item) throws ItemAlreadyExistsException {
        Optional<Item> item1 = itemRepository.findItemByBarcode(item.getBarcode());
        if(item1.isPresent()) {
            throw new ItemAlreadyExistsException("item with barcode: " + item1.get().getBarcode() + " already exist");
        }
       return itemRepository.save(item);
    }
    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    public Optional<Item> getItemByBarcode(String barcode) {
        return itemRepository.findItemByBarcode(barcode);
    }

    @Transactional
    public void sell(List<OrderItem> orderItems){
        for (OrderItem orderItem: orderItems){
            itemRepository.sellItem(orderItem.getQuantity(), orderItem.getItemId());
        }

    }
}
