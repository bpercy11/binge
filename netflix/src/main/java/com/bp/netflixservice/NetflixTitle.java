package com.bp.netflixservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NetflixTitle {
    private String title;
    private String title_type;
    private String synopsis;
    private int year;
    private LocalDateTime createdAt = LocalDateTime.now();

}
