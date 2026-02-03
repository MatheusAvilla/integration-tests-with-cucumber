package com.example.testeintegracao.repository;

import com.example.testeintegracao.domain.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("""
            SELECT i FROM Item i
            WHERE (:lastId IS NULL OR i.id > :lastId)
            ORDER BY i.id ASC
            """)
//    SELECT *
//    FROM item
//    WHERE id > 5
//    ORDER BY id ASC
//    LIMIT 5;
    List<Item> fetchNextPage(@Param("lastId") Long lastId, Pageable pageable);

}
