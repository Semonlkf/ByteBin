package com.zyq.HitBytebin.config;

import com.zyq.HitBytebin.serivce.ArticleCommentService;
import com.zyq.HitBytebin.serivce.ArticleService;
import com.zyq.HitBytebin.serivce.ReplyCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApplicationListens implements DisposableBean {
    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleCommentService articleCommentService;
    @Autowired
    ReplyCommentService replyCommentService;

    @Override
    public void destroy() throws Exception {
        log.info("应用程序正在关闭，清理相关数据...");
        articleService.saveLikeToDB();
        articleService.updateDBView();
        articleService.updateCommentNum();
        articleCommentService.saveLikeToDB();
        replyCommentService.saveLikeToDB();
        log.info("清理结束");
    }
}
