package com.example.testeintegracao.controller;

import com.example.testeintegracao.domain.Item;
import com.example.testeintegracao.dto.CursorPageResponseDTO;
import com.example.testeintegracao.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/offset-pagination")
    public ResponseEntity<Page<Item>> listItems(@RequestParam(name = "page", defaultValue = "0") int page,
                                                @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<Item> items = itemService.getItemsOffset(page, size);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/keyset-pagination")
    public ResponseEntity<CursorPageResponseDTO<Item>> listItems(@RequestParam(name = "lastId", required = false) Long lastId,
                                                                 @RequestParam(name = "size", defaultValue = "10") int size) {
        CursorPageResponseDTO<Item> items = itemService.getItemsKeyset(lastId, size);
        return ResponseEntity.ok(items);
    }

}
