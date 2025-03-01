package com.myapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UrlDetailsDTO implements Serializable {
    private String shortenedUrl;
    private String originalUrl;
    private LocalDateTime creationTime;
}
