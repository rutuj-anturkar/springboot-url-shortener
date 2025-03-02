package com.myapp.repository;

import com.myapp.model.ShortenedUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShortenedUrlRepository extends JpaRepository<ShortenedUrl,Long> {
    Optional<ShortenedUrl> findByShortenedUrl(String shortenedUrl);
    Optional<ShortenedUrl> findByOriginalUrl(String originalUrl);
}
