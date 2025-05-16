package com.ic.agile.modules.dream.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 用户梦境
 *
 * @author zhangzc xxx
 * @since 1.0.0 2025-04-28
 */
@Data
@TableName("zz_dreams")
public class DreamsEntity {

    /**
     * id
     */
	private Long id;
    /**
     * 用户id
     */
	private String userId;
    /**
     * 原始梦境
     */
	private String originalDream;
    /**
     * 梦境故事
     */
	private String generatedStory;
    /**
     * 梦境含义
     */
	private String dreamMeaning;
    /**
     * 
     */
	private Date createdAt;
    /**
     * 
     */
	private Date updatedAt;
    /**
     * 
     */
	private Integer isDeleted;
}