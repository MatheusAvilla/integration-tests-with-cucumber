package com.example.testeintegracao.service;

import com.example.testeintegracao.domain.Item;
import com.example.testeintegracao.dto.CursorPageResponseDTO;
import com.example.testeintegracao.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Page<Item> getItemsOffset(int offset, int pageSize) {
        return itemRepository.findAll(PageRequest.of(offset, pageSize));
    }

    public CursorPageResponseDTO<Item> getItemsKeyset(Long lastId, int size) {
        // default page = 0, size = 10 [0-9]
        Pageable pageable = PageRequest.of(0, size);

        // fetch next page records
        List<Item> items = itemRepository.fetchNextPage(lastId, pageable);

        // check if we have more records
        boolean hasNext = items.size() == size;

        // determine the next cursor
        Long nextCursor = hasNext ? items.get(items.size() - 1).getId() : null;

        return new CursorPageResponseDTO<>(
                items,
                size,
                nextCursor,
                hasNext
        );
    }

}
