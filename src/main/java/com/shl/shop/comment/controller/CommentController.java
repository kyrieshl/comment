package com.shl.shop.comment.controller;

import com.shl.shop.comment.Result.Result;
import com.shl.shop.comment.enums.ResultEnum;
import com.shl.shop.comment.model.Comment;
import com.shl.shop.comment.service.CommentService;
import com.shl.shop.comment.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;


    /**
     *发表评论
     *
     * 对于评论，先检查用户是否有权限
     * 1.如果没有购买过商品，则不能发表评论
     * 2.如果用户购买商品后规定时间内没有评论，则过期也不能再评论
     *
     * @param userId
     * @param comment
     * @return
     */
    @PostMapping("/addComment")
    public Result addComment(@RequestParam("userId") Integer userId,
                             @ModelAttribute Comment comment){
        if(userId == null)
            return ResultUtils.wrapResult(ResultEnum.FAIL,"请先登录！");
        if(comment == null)
            return ResultUtils.wrapResult(ResultEnum.FAIL,"请评论！");

        comment.setAddTime(new Date());
        comment.setUserId(userId);
        if(comment.getPicUrls() == null)
            comment.setHasPicture(false);
        else
            comment.setHasPicture(true);
        comment.setDeleted(false);
        return ResultUtils.wrapResult(ResultEnum.SUCCESS,commentService.addComment(comment));
    }

    /**
     *
     * @param typeId 类型ID。 如果是0，则查询商品评论；如果是1，则查询专题评论。
     * @param valueId 商品或专题ID。如果typeId是0，则是商品ID；如果typeId是1，则是专题ID。
     * @return
     */
    @GetMapping("/commentCount")
    public Result commentCount(@RequestParam("typeId") Byte typeId,
                               @RequestParam("valueId") Integer valueId){
        int allCommentCount = commentService.commentCount(typeId,valueId);
        int commentCountWithPic = commentService.commentCountWithPic(typeId,valueId);
        Map<String,Object> data = new HashMap<>();
        data.put("allCount",allCommentCount);
        data.put("hasPicCount",commentCountWithPic);
        return ResultUtils.wrapResult(ResultEnum.SUCCESS,data);
    }

    /**
     *
     * 评论列表
     *
     * @param typeId 类型ID。 如果是0，则查询商品评论；如果是1，则查询专题评论。
     * @param valueId 商品或专题ID。如果typeId是0，则是商品ID；如果typeId是1，则是专题ID。
     * @param showType 显示类型。如果是0，则查询全部；如果是1，则查询有图片的评论。
     * @param page 分页页数
     * @param size 分页大小
     * @return 评论列表
     */
    @GetMapping("/allComment")
    public Result allComment(@RequestParam("typeId") Byte typeId,
                             @RequestParam("valueId") Integer valueId,
                             @RequestParam("showType") Integer showType,
                             @RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size){
        List<Comment> commentList = null;
        Integer count = null;
        if(showType == 0){
            commentList = commentService.allComment(typeId,valueId);
            count = commentService.commentCount(typeId,valueId);
        }
        if(showType == 1){
            commentList = commentService.allCommentWithPic(typeId,valueId);
            count = commentService.commentCountWithPic(typeId,valueId);
        }

        List<Map<String,Object>> commentVoList = new ArrayList<>(commentList.size());
        commentList.forEach(comment -> {
            Map<String,Object> commentVo = new HashMap<>();
            commentVo.put("userId",comment.getUserId());
            commentVo.put("addTime",comment.getAddTime());
            commentVo.put("content",comment.getContent());
            commentVo.put("picList",comment.getPicUrls());

            commentVoList.add(commentVo);
        });
        Map<String,Object> data = new HashMap<>();
        data.put("data",commentVoList);
        data.put("count",count);
        data.put("currentPage",page);
        return ResultUtils.wrapResult(ResultEnum.SUCCESS,data);
    }
}
