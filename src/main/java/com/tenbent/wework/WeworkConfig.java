package com.tenbent.wework;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;

public class WeworkConfig {
    public String agentId="1000002";
    public String secret="Qu6ja5nw_qqAJhY3zLqUxEPVbdGD0y0AtwWu8OMwVTA";
    public String corpid = "ww44dc1bfb614f7354";
    public String contactSecret="mgImouSZLWpb6m561bvuI8AWcXPxPJ5oQhxl99eEtSY";

    private static WeworkConfig weworkConfig;
    public static WeworkConfig getInstance(){
        if(weworkConfig==null){
            weworkConfig=new WeworkConfig();
        }
        return weworkConfig;}

    public static WeworkConfig load(String path) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            return mapper.readValue(WeworkConfig.class.getResourceAsStream(path),WeworkConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(mapper.writeValueAsString(WeworkConfig.getInstance()));
        return null;
    }
}
