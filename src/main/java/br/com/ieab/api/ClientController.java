package br.com.ieab.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static br.com.ieab.Commons.KEY;
import static br.com.ieab.Commons.hashToName;
import static java.lang.Long.parseLong;
import static java.util.Collections.sort;

/**
 * Created by montanha on 29/10/17.
 */
@Controller
public class ClientController {

    @Autowired
    private RedisTemplate<String, String> stringRedisTemplate;

    @GetMapping("/show")
    public String show(Map<String, Object> model) {
        HashOperations<String, String, String> hashOperations = this.stringRedisTemplate.opsForHash();
        Map<String, String> teensMap = hashOperations.entries(KEY);

        List<Teen> teenList = new ArrayList();

        teensMap.forEach((hash, point) -> teenList.add(new Teen(parseLong(point), hashToName(hash))));

        sort(teenList);

        model.put("teenList", teenList);

        return "client";
    }
}