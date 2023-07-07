package com.example.ckb.article;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleForm {

    @NotEmpty
    private String subject;

    @NotEmpty
    private String content;
}
