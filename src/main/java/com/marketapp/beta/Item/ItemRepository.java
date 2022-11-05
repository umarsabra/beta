package com.marketapp.beta.Item;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query(value = "UPDATE item i SET i.quantity = i.quantity - ?1 WHERE i.barcode = ?2", nativeQuery = true)
    void sellItem(Integer quantity, Long barcode);

    Item findItemByBarcode(Long barcode);
}
