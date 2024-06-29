package com.zyq.HitBytebin.serivce;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyq.HitBytebin.model.entity.ThumbsUp;
import com.zyq.HitBytebin.model.pojo.ReplyComment;
import com.zyq.HitBytebin.model.vo.CommentVo;

import java.util.ArrayList;
import java.util.List;

public interface ReplyCommentService {
    CommentVo post(ReplyComment replyComment);

    Boolean del(Long id, Long userId);

    Boolean del(ArrayList<Long> ids);

    List<ReplyComment> record(Long userId);

    List<CommentVo> getReplies(Long id, Long userId);

    Page<CommentVo> getReplies(Long id, int page);

    void like(ThumbsUp thumbs);

    void cancelLike(ThumbsUp thumbs);

    void saveLikeToDB();

    void updateLikeNum(long id, int num);

    boolean getIsLiked(Long userId, Long replyId);
}
