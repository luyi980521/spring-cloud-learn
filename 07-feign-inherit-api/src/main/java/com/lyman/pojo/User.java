package com.lyman.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by luyi on 2021/5/6 23:04.
 * 描述：TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer userId;
    private String userName;
    private Integer age;
}
