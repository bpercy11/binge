package com.bp.netflixservice;

import java.util.ArrayList;
import java.util.List;

public class NetflixToTitleConverter {
    public static List<Title> convertTitles(List<NetflixTitle> netflixTitles){
        List<Title> titles = new ArrayList<>();
        for(NetflixTitle netflix : netflixTitles){
            Title title = new Title();
            title.setTitle(netflix.getTitle());
            title.setTitleType(netflix.getTitle_type());
            title.setYear(netflix.getYear());
            title.setCreatedAt(netflix.getCreatedAt());
            title.setPlatform("NETFLIX");

            titles.add(title);
        }

        return titles;
    }
}
