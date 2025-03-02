package com.myapp.com.myapp.service;

import com.myapp.dto.ShortenUrlRequestDTO;
import com.myapp.dto.ShortenUrlResponseDTO;
import com.myapp.dto.UrlDetailsDTO;

public interface ShortenUrlService {
    UrlDetailsDTO getShortenedUrl(ShortenUrlRequestDTO shortenUrlRequestDTO);
    //String getOriginalUrl(String shortUrl);

    UrlDetailsDTO getUrlDetails(String shortUrl);

    void deleteUrlByShortenedUrl(String shortUrl);
}
