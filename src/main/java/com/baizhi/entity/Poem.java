package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "article", type = "poem")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Poem {

    @Id
    private String id;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String name;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String author;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String type;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String content;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String href;
    @Field(type = FieldType.Keyword)
    private String authordes;
    @Field(type = FieldType.Keyword)
    private String origin;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String categoryName;
}
