package com.marketapp.beta.Item;


import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.List;

@Configuration
public class ItemConfig {
    @Bean
    CommandLineRunner commandLineRunnerItem(
            ItemRepository itemRepository
    ){
        return  args -> {


            Item item1 = Item.builder()
                    .barcode("6221234567891")
                    .title("Pepsi 350ml")
                    .unitType(UnitType.PC)
                    .pricePerUnit(6F)
                    .quantity(6)
                    .weightPerQuantity(2100f)
                    .costPerQuantity(5f)
                    .build();


            Item item2 =  Item.builder()
                    .barcode("123456")
                    .title("Green Soap")
                    .unitType(UnitType.L)
                    .pricePerUnit(6f)
                    .quantity(1)
                    .costPerQuantity(50f)
                    .weightPerQuantity(60f)
                    .build();


            itemRepository.saveAll(List.of(item1, item2));

        };
    }


}
