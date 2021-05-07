package com.lyman.controller;

import com.lyman.pojo.HouseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by luyi on 2021/5/5 18:45.
 * 描述：在springCloud中使用ribbon
 */
@Slf4j
@RestController
public class HouseController {

    @GetMapping("/house/data")
    public HouseInfo getData(@RequestParam("name") String name) {
        log.info("请求已到达");
        return new HouseInfo(1, "上海", "虹口", "东体小区");
    }

    @GetMapping("/house/data/{name}")
    public String getData2(@PathVariable("name") String name) {
        return name;
    }

    @PostMapping("/house/save")
    public Integer addData(HouseInfo houseInfo) {
        log.info("houseInfo: {}", houseInfo);
        return 200;
    }

    @GetMapping("/houseData")
    public String houseData() {
        return "houseData";
    }
}
