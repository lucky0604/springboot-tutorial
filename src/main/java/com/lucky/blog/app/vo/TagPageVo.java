package com.lucky.blog.app.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagPageVo {
    Integer offset;
    Integer pageSize;
    Integer tagId;
}
