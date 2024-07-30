package com.example.UrlShortenerApi.domain.dto;

import java.time.LocalDateTime;

public record UrlDTO(String shortenedUrl, String originalUrl, LocalDateTime creationDate, LocalDateTime lastClickDate) {
}
