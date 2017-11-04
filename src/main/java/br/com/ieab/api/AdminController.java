package br.com.ieab.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static br.com.ieab.Commons.KEY;
import static java.lang.Long.parseLong;
import static java.lang.String.valueOf;
import static java.util.Collections.sort;

/**
 * Created by montanha on 29/10/17.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RedisTemplate<String, String> stringRedisTemplate;

    @GetMapping
    public String admin(Map<String, Object> model) {
        HashOperations<String, String, String> hashOperations = this.stringRedisTemplate.opsForHash();
        Map<String, String> teensMap = hashOperations.entries(KEY);

        List<Teen> teenList = new ArrayList();

        teensMap.forEach((hash, point) -> teenList.add(new Teen(parseLong(point), hash)));

        sort(teenList);

        model.put("teenList", teenList);

        return "admin";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("teen") Teen teen) {
        if (teen != null) {
            stringRedisTemplate.opsForHash().put(KEY, teen.getHash(), valueOf(teen.getPoints()));
        }

        return "redirect:/admin";
    }

    @PostMapping("/incrise")
    public String incrisePoint(@ModelAttribute("teen") Teen teen) {
        if (teen != null) {
            stringRedisTemplate.opsForHash().increment(KEY, teen.getHash(), teen.getPoints());
        }

        return "redirect:/admin";
    }

    @GetMapping("/delete/{hash}")
    public String delete(@PathVariable("hash") String hash) {
        if (hash != null && !hash.isEmpty()) {
            stringRedisTemplate.opsForHash().delete(KEY, hash);
        }

        return "redirect:/admin";
    }
}