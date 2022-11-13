package com.marketapp.beta.Item;


import com.marketapp.beta.Exception.ItemAlreadyExistsException;
import com.marketapp.beta.Exception.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/items")
public class ItemController {

    @Autowired
    ItemService itemService;

    @PostMapping
    public Item createItem(@RequestBody @Valid Item item) throws ItemAlreadyExistsException {
        return itemService.addItem(item);
    }

    @GetMapping
    public ResponseEntity<List<Item>> getItems(){

        List<Item> items = itemService.getAllItems();
        return ResponseEntity.ok().body(items);
    }

    @GetMapping("/{barcode}")
    public ResponseEntity<Item> getItemByBarcode(@PathVariable String barcode) throws ItemNotFoundException {
        Optional<Item> item = itemService.getItemByBarcode(barcode);
        if(item.isEmpty()){
            throw new ItemNotFoundException("Item with barcode: "+ barcode +" was not found");
        }
        return ResponseEntity.ok(item.get());
    }

}
