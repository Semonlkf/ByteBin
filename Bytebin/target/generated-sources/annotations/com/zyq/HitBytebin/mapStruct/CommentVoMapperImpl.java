package com.zyq.HitBytebin.mapStruct;

import com.zyq.HitBytebin.model.pojo.ArticleComment;
import com.zyq.HitBytebin.model.pojo.ReplyComment;
import com.zyq.HitBytebin.model.pojo.User;
import com.zyq.HitBytebin.model.vo.CommentVo;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-14T15:22:43+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class CommentVoMapperImpl implements CommentVoMapper {

    @Override
    public CommentVo toVo(ArticleComment articleComment) {
        if ( articleComment == null ) {
            return null;
        }

        CommentVo commentVo = new CommentVo();

        commentVo.setCommentId( longToStr( articleComment.getId() ) );
        if ( articleComment.getUserId() != null ) {
            commentVo.setUserId( articleComment.getUserId().intValue() );
        }
        commentVo.setContent( articleComment.getContent() );
        commentVo.setCreateTime( articleComment.getCreateTime() );
        commentVo.setLikeNum( articleComment.getLikeNum() );

        return commentVo;
    }

    @Override
    public CommentVo toVo(ReplyComment replyComment) {
        if ( replyComment == null ) {
            return null;
        }

        CommentVo commentVo = new CommentVo();

        commentVo.setReplyId( longToStr( replyComment.getId() ) );
        commentVo.setCommentId( longToStr( replyComment.getCommentId() ) );
        if ( replyComment.getUserId() != null ) {
            commentVo.setUserId( replyComment.getUserId().intValue() );
        }
        commentVo.setContent( replyComment.getContent() );
        commentVo.setCreateTime( replyComment.getCreateTime() );
        commentVo.setLikeNum( replyComment.getLikeNum() );
        commentVo.setType( replyComment.getType() );

        return commentVo;
    }

    @Override
    public CommentVo toVo(User user, ArticleComment articleComment) {
        if ( user == null && articleComment == null ) {
            return null;
        }

        CommentVo commentVo = new CommentVo();

        if ( user != null ) {
            if ( user.getId() != null ) {
                commentVo.setUserId( user.getId().intValue() );
            }
            commentVo.setUsername( user.getUsername() );
            commentVo.setNickname( user.getNickname() );
            commentVo.setAvatar( user.getAvatar() );
        }
        if ( articleComment != null ) {
            commentVo.setCommentId( longToStr( articleComment.getId() ) );
            commentVo.setContent( articleComment.getContent() );
            commentVo.setCreateTime( articleComment.getCreateTime() );
            commentVo.setLikeNum( articleComment.getLikeNum() );
        }

        return commentVo;
    }

    @Override
    public CommentVo toVo(User user, ReplyComment replyComment) {
        if ( user == null && replyComment == null ) {
            return null;
        }

        CommentVo commentVo = new CommentVo();

        if ( user != null ) {
            if ( user.getId() != null ) {
                commentVo.setUserId( user.getId().intValue() );
            }
            commentVo.setUsername( user.getUsername() );
            commentVo.setNickname( user.getNickname() );
            commentVo.setAvatar( user.getAvatar() );
        }
        if ( replyComment != null ) {
            commentVo.setReplyId( longToStr( replyComment.getId() ) );
            commentVo.setCommentId( longToStr( replyComment.getCommentId() ) );
            commentVo.setContent( replyComment.getContent() );
            commentVo.setCreateTime( replyComment.getCreateTime() );
            commentVo.setLikeNum( replyComment.getLikeNum() );
            commentVo.setType( replyComment.getType() );
        }

        return commentVo;
    }
}
