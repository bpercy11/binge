package com.bp.netflixservice;

import com.bp.util.ObjectMapperSingleton;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class NetflixService {
    private final String BASE_URL = "https://unogs-unogs-v1.p.rapidapi.com/search/titles?order_by=date&type=movie";
    private final NetflixRepository netflixRepository;

    public void start() {
        try {
            String jsonString = getJsonString();
            List<NetflixTitle> netflixTitles = convertJsonToNetflixTitles(jsonString);
            List<Title> titles = NetflixToTitleConverter.convertTitles(netflixTitles);

//            netflixRepository.save(titles.get(0));
            for(Title title : titles){
                try{
                    netflixRepository.save(title);
                } catch(Exception e){
                    log.error("Error inserting {} into titles databse", title.toString());
                }
            }
        } catch (Exception e){
            log.error("Error uploading Netflix titles to Title database");
            log.error(e.getMessage());
        }

        log.info("Completed upload of Netflix titles to Title database");
    }

    private List<NetflixTitle> convertJsonToNetflixTitles(String jsonString){
        ObjectMapper objMap = ObjectMapperSingleton.getInstance();
        List<NetflixTitle> netflixTitles;
        try {
            netflixTitles = objMap.readValue(jsonString, new TypeReference<List<NetflixTitle>>(){});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return netflixTitles;
    }

    private HttpResponse<String> getNetflixTitles(){
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://unogs-unogs-v1.p.rapidapi.com/search/titles?order_by=date&type=movie"))
//                .header("X-RapidAPI-Key", "900dbcd665msh77c52b06e9f0209p118b89jsn34e17bf8a909")
//                .header("X-RapidAPI-Host", "unogs-unogs-v1.p.rapidapi.com")
//                .method("GET", HttpRequest.BodyPublishers.noBody())
//                .build();
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//        ObjectMapper objMapper = new ObjectMapper();
        return null;
    }

    private String getJsonString(){
        try(FileReader fr = new FileReader("/Users/brett/Development/binge/binge/netflix-titles.json")) {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(fr);
            JSONArray jsonArray = (JSONArray) jsonObject.get("results");
            return jsonArray.toJSONString();
        } catch (IOException | ParseException e){
            log.error("Error retrieving/converting Netflix titles response");
        }

        return null;
    }
}
