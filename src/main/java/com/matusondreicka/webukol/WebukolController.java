package com.matusondreicka.webukol;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WebukolController {

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

}
