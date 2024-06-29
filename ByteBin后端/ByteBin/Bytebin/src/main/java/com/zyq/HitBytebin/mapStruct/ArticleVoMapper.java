package com.zyq.HitBytebin.mapStruct;

import com.zyq.HitBytebin.model.pojo.Article;
import com.zyq.HitBytebin.model.vo.ArticleVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface ArticleVoMapper {
    @Mapping(target = "tags", source = "tags", qualifiedByName = "setTags")
    ArticleVo toVo(Article article);

    @Named("setTags")
    default String[] setTags(String[] tags) {
        if (Objects.isNull(tags)) {
            return new String[0];
        }
        return tags;
    }
}
