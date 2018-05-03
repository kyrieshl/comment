package com.shl.shop.comment.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Table(name = "comment")
@Entity
public class Comment implements Serializable{

    private static final long serialVersionUID = 8091070222718686714L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer commentId;

    private Integer userId;

    private Byte typeId;

    private Integer valueId;

    private String content;

    private Boolean hasPicture;

    private String[] picUrls;

    private Short star;

    private Date addTime;

    private Boolean deleted;

    public Comment(){

    }

}
