package com.lyman.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by luyi on 2021/5/5 18:46.
 * 描述：房子信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseInfo {

    private Integer houseId;
    private String city;
    private String district;
    private String address;
}
