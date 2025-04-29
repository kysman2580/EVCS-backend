package com.example.evcs.news.model.service;

import com.example.evcs.news.model.dao.CommentReactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentReactionServiceImpl implements CommentReactionService {

    private final CommentReactionMapper commentReactionMapper;

    @Override
    public void like(Long newsCmtId, Long memberNo) {
        commentReactionMapper.insertLike(newsCmtId, memberNo);
    }

    @Override
    public void cancelLike(Long newsCmtId, Long memberNo) {
        commentReactionMapper.deleteLike(newsCmtId, memberNo);
    }

    @Override
    public void hate(Long newsCmtId, Long memberNo) {
        commentReactionMapper.insertHate(newsCmtId, memberNo);
    }

    @Override
    public void cancelHate(Long newsCmtId, Long memberNo) {
        commentReactionMapper.deleteHate(newsCmtId, memberNo);
    }

    @Override
    public int getLikeCount(Long newsCmtId) {
        return commentReactionMapper.countLikes(newsCmtId);
    }

    @Override
    public int getHateCount(Long newsCmtId) {
        return commentReactionMapper.countHates(newsCmtId);
    }

    @Override
    public boolean hasLiked(Long newsCmtId, Long memberNo) {
        return commentReactionMapper.checkLike(newsCmtId, memberNo) > 0;
    }

    @Override
    public boolean hasHated(Long newsCmtId, Long memberNo) {
        return commentReactionMapper.checkHate(newsCmtId, memberNo) > 0;
    }
}
