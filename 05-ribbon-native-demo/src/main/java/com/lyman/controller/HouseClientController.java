package com.lyman.controller;

import com.lyman.pojo.HouseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by luyi on 2021/5/5 18:51.
 * 描述：用于请求houseController的接口
 */
@Slf4j
@RestController
public class HouseClientController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/call/data")
    public HouseInfo getData(@RequestParam("name") String name) {
//        return restTemplate.getForObject("http://localhost:8081/house/data?name=" + name, HouseInfo.class);
//        ResponseEntity<HouseInfo> responseEntity
//                = restTemplate.getForEntity("http://localhost:8081/house/data?name="
//                + name, HouseInfo.class);
//        if (responseEntity.getStatusCode() == HttpStatus.OK) {
//            return responseEntity.getBody();
//        }
//        return null;
        return restTemplate.getForObject("http://user-service/house/data?name=" + name, HouseInfo.class);
    }

    @GetMapping("/call/data/{name}")
    public String getData2(@PathVariable("name") String name) {
        return restTemplate.getForObject("http://localhost:8081/house/data/{name}",
                String.class, name);
    }
}
