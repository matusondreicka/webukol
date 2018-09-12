package com.matusondreicka.webukol;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONObject;

@RestController
public class WebukolController {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    @RequestMapping("/greeting")
    @GetMapping
    List greeting() {

        List<Map> result = new ArrayList<>();
        Map map;

        map = new HashMap<String, Integer>();
        map.put("ahoj", 1);
        map.put("bye", 2);
        result.add(map);

        map = new HashMap<String, String>();
        map.put("ahoj", "1");
        map.put("bye", "2");
        result.add(map);

        return result;
    }

    @RequestMapping("/players")
    @GetMapping

    List<Map> players() throws Exception {
        //JSONArray

//        JSONObject json = readJsonFromUrl("https://stats.nba.com/stats/scoreboard/?GameDate=02/14/2015&LeagueID=00&DayOffset=0");
//
//        System.out.println(json.toString());
//        System.out.println(json.get("id"));

        //List<String> result = new ArrayList<>();

//        result.add(""+json.get("id"));

        try {
            call_me();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String response = "";
        try {
            URL url = new URL("http://api.probasketballapi.com/player?api_key=QUBEVr3dsCM1R5a8k0jzFKwocqDJ6fXS");

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            //conn.getOutputStream().write(postDataBytes);
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

//            System.out.println(obj.toMap());

            resultList.add(obj.toMap());

//            String name = obj.getString("name");
//            String url = obj.getString("url");

//            System.out.println(name);
//            System.out.println(url);
        }


        //return response.toString();
        //return new JSONObject(response.toString());
        //return new JSONArray(response.toString());

        return resultList;
    }

    public static void call_me() throws Exception {
        URL url = new URL("http://httpbin.org/post");
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("name", "Jinu Jawad");
        params.put("email", "helloworld@gmail.com");
        params.put("CODE", 1111);
        params.put("message", "Hello Post Test success");
        StringBuilder postData = new StringBuilder();
        for (Map.Entry param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey().toString(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);
        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (int c; (c = in.read()) >= 0;)
            sb.append((char)c);
        String response = sb.toString();
        System.out.println(response);
        JSONObject myResponse = new JSONObject(response.toString());
        System.out.println("result after Reading JSON Response");
        System.out.println("origin- "+myResponse.getString("origin"));
        System.out.println("url- "+myResponse.getString("url"));
        JSONObject form_data = myResponse.getJSONObject("form");
        System.out.println("CODE- "+form_data.getString("CODE"));
        System.out.println("email- "+form_data.getString("email"));
        System.out.println("message- "+form_data.getString("message"));
        System.out.println("name"+form_data.getString("name"));
    }

}
