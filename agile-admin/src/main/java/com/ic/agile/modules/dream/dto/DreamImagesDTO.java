package com.ic.agile.modules.dream.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 梦境图片
 *
 * @author zhangzc xxx
 * @since 1.0.0 2025-04-28
 */
@Data
@ApiModel(value = "梦境图片")
public class DreamImagesDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private String id;

	@ApiModelProperty(value = "梦境id")
	private String dreamId;

	@ApiModelProperty(value = "图片URL")
	private String imageUrl;

	@ApiModelProperty(value = "排序")
	private Integer sortOrder;

	@ApiModelProperty(value = "")
	private Date createdAt;


}