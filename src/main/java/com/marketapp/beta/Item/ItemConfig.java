package com.marketapp.beta.Item;


import com.marketapp.beta.ItemPackage.ItemPackage;
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

            ItemPackage itemPackage1 = ItemPackage.builder()
                    .barcode("6221234567810")
                    .costPerPackage(30f)
                    .packageType(ItemPackage.PackageType.PACKAGE)
                    .weightPerPackage(2100f)
                    .quantity(1)
                    .pricePerPackage(36f)
                    .build();

            Item item1 = Item.builder()
                    .barcode("6221234567891")
                    .title("Pepsi 350ml")
                    .unitType(UnitType.PC)
                    .pricePerUnit(6F)
                    .quantity(6)
                    .weightPerQuantity(2100f)
                    .itemPackage(itemPackage1)
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
