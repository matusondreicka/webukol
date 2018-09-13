package com.matusondreicka.webukol;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import org.json.JSONObject;
import org.json.JSONArray;

import java.net.HttpURLConnection;

@RestController
public class WebukolController {

    @RequestMapping("/players")
    @GetMapping
    List<Map> players() throws Exception {

        String response = "";
        try {
            URL url = new URL("http://api.probasketballapi.com/player?api_key=QUBEVr3dsCM1R5a8k0jzFKwocqDJ6fXS");

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);
            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;)
                sb.append((char)c);
            response = sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONArray jsonarray = new JSONArray(response);

        List<Map> resultList = new ArrayList<>();

        System.out.println("players total count: " + jsonarray.length());

        for(int i=0; i<jsonarray.length(); i++){
            JSONObject obj = jsonarray.getJSONObject(i);
            resultList.add(obj.toMap());
        }

        return resultList;
    }


}
