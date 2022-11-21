package com.marketapp.beta.Item;


import com.marketapp.beta.Dto.ItemCreationDot;
import com.marketapp.beta.Exception.ItemAlreadyExistsException;
import com.marketapp.beta.Exception.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Item> createItem(@RequestBody @Valid ItemCreationDot itemRequest) throws ItemAlreadyExistsException {
        Item item =  Item.builder()
                .title(itemRequest.getTitle())
                .barcode(itemRequest.getBarcode())
                .unitType(itemRequest.getUnitType())
                .pricePerUnit(itemRequest.getPricePerUnit())
                .quantity(itemRequest.getQuantity())
                .costPerQuantity(itemRequest.getCostPerQuantity())
                .weightPerQuantity(itemRequest.getWeightPerQuantity())
                .netWeight(itemRequest.getNetWeight())
                .build();
        return new ResponseEntity<>(itemService.addItem(item), HttpStatus.CREATED);
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
