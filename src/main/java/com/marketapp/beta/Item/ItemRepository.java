package com.marketapp.beta.Item;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Modifying
    @Query("UPDATE Item i SET i.quantity = i.quantity - ?1 WHERE i.id = ?2")
    void sellPieceItem(Integer quantity, Long itemId);

    @Modifying
    @Query("UPDATE Item i SET i.net_weight = i.net_weight - ?1 WHERE i.id = ?2")
    void sellWeightItem(Float weight, Long id);

    Optional<Item> findItemByBarcode(String barcode);
}
