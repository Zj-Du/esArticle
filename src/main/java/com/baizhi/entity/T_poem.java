package com.baizhi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class T_poem implements Serializable {

    private String id;
    private String name;
    private String author;
    private String type;
    private String content;
    private String href;
    private String authordes;
    private String origin;
    private String categoryId;
    private T_category t_category;
}
