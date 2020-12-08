package com.yc.learning.domain;

import lombok.Data;

@Data
public class ChapterDomain extends PageDomain{
    private Integer chid;
    private String cname;
    private Integer pchid;
    private Integer cid;

    public ChapterDomain(Integer chid, String cname, Integer pchid, Integer cid) {
        this.chid = chid;
        this.cname = cname;
        this.pchid = pchid;
        this.cid = cid;
    }

    public ChapterDomain() {
    }
}
