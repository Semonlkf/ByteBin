package com.zyq.bloggy.mapStruct;

import com.zyq.bloggy.model.pojo.Article;
import com.zyq.bloggy.model.vo.ArticleVo;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-07T20:44:49+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
@Component
public class ArticleVoMapperImpl implements ArticleVoMapper {

    @Override
    public ArticleVo toVo(Article article) {
        if ( article == null ) {
            return null;
        }

        ArticleVo articleVo = new ArticleVo();

        articleVo.setTags( setTags( article.getTags() ) );
        articleVo.setId( article.getId() );
        articleVo.setTitle( article.getTitle() );
        articleVo.setContent( article.getContent() );
        articleVo.setStatus( article.getStatus() );
        articleVo.setViews( article.getViews() );
        articleVo.setComments( article.getComments() );
        articleVo.setLikeNum( article.getLikeNum() );
        articleVo.setCreateTime( article.getCreateTime() );
        articleVo.setUpdateTime( article.getUpdateTime() );
        articleVo.setDescription( article.getDescription() );

        return articleVo;
    }
}
