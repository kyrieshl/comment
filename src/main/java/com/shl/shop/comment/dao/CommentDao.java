package com.shl.shop.comment.dao;

import com.shl.shop.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao extends JpaRepository<Comment,Integer>{
    List<Comment> findAllByTypeIdAndValueId(Byte typeId,Integer valueId);
    List<Comment> findAllByTypeIdAndValueIdAndHasPicture(Byte typeId,Integer valueId,Boolean hasPicture);

}
