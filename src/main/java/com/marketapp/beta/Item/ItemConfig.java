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
                    .barcode(6221234567891L)
                    .title("Pepsi 350ml")
                    .price(6F)
                    .quantity(20)
                    .totalCost(100F)
                    .build();


            Item item2 =  Item.builder()
                    .barcode(6221234567892L)
                    .title("Milk 500ml")
                    .price(6F)
                    .quantity(20)
                    .totalCost(100F)
                    .build();


            itemRepository.saveAll(List.of(item1, item2));

        };
    }


}
