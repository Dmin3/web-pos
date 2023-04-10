package com.example.webpos.item.repository;

import com.example.webpos.item.domain.Item;
import com.example.webpos.item.exception.ItemNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {

    default Item getById(final Long id) {
        return findById(id).orElseThrow(ItemNotFoundException::new);
    }
}
