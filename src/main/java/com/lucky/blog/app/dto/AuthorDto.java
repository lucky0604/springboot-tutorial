package com.lucky.blog.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto implements Serializable {
    private static final long serialVersionUID = 5059212992463947120L;

    private Long id;
    private String avatar;
    private String nickname;
}
