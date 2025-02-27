package com.myapp.repository;

import com.myapp.model.ShortenedUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShortenedUrlRepository extends JpaRepository<ShortenedUrl,Long> {
    List<ShortenedUrl> findByShortenedUrl(String shortenedUrl);
    List<ShortenedUrl> findByOriginalUrl(String originalUrl);
}
