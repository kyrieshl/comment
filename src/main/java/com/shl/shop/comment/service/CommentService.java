package com.shl.shop.comment.service;

import com.shl.shop.comment.dao.CommentDao;
import com.shl.shop.comment.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

//    保存评论
    public Comment addComment(Comment comment){
        return commentDao.save(comment);
    }

//    通过typeId和valueId查询所有评论
    public List<Comment> allComment(Byte typeId,Integer valueId){
        return commentDao.findAllByTypeIdAndValueId(typeId,valueId);
    }

//    通过typeId和valueId查询所有带图片的评论
    public List<Comment> allCommentWithPic(Byte typeId,Integer valueId){
        return commentDao.findAllByTypeIdAndValueIdAndHasPicture(typeId,valueId,true);
    }

//    通过typeId和valueId查询评论数量
    public Integer commentCount(Byte typeId,Integer valueId){
        return commentDao.findAllByTypeIdAndValueId(typeId,valueId).size();
    }

//    通过typeId和valueId查询带图片的评论数量
    public Integer commentCountWithPic(Byte typeId,Integer valueId){
        return commentDao.findAllByTypeIdAndValueIdAndHasPicture(typeId,valueId,true).size();
    }
}
