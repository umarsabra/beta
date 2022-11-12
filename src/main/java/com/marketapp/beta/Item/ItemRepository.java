package com.marketapp.beta.Item;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Modifying
    @Query("UPDATE Item i SET i.quantity = i.quantity - ?1 WHERE i.id = ?2")
    void sellItem(Integer quantity, Long itemId);

    Optional<Item> findItemByBarcode(String barcode);
}
