package com.example.testeintegracao.dto;

import java.util.List;

public record CursorPageResponseDTO<T>(
        List<T> data,
        int pageSize,
        Long nextCursor,
        boolean hasNext
) {}
