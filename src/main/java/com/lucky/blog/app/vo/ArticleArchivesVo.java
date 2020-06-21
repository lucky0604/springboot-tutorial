package com.lucky.blog.app.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleArchivesVo {
    private String year;
    private String month;
    private Integer count;
}
