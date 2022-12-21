package com.bp.netflixservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class NetflixResponse implements Serializable {
    private String title;
    private String title_type;
    private String synopsis;
    private int rating;
    private int year;
    private String imbd_id;
}
