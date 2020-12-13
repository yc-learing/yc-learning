package com.yc.learning.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChapterDomain extends PageDomain{
    private Integer chid;
    private String cname;
    private Integer cid;
    private String temp;


}
