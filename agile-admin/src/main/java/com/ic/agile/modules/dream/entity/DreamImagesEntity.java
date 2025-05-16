package com.ic.agile.modules.dream.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 梦境图片
 *
 * @author zhangzc xxx
 * @since 1.0.0 2025-04-28
 */
@Data
@TableName("zz_dream_images")
public class DreamImagesEntity {

    /**
     * id
     */
	private String id;
    /**
     * 梦境id
     */
	private String dreamId;
    /**
     * 图片URL
     */
	private String imageUrl;
    /**
     * 排序
     */
	private Integer sortOrder;
    /**
     * 
     */
	private Date createdAt;
}