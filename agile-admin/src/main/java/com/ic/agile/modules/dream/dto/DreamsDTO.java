package com.ic.agile.modules.dream.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户梦境
 *
 * @author zhangzc xxx
 * @since 1.0.0 2025-04-28
 */
@Data
@ApiModel(value = "用户梦境")
public class DreamsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "用户id")
	private String userId;

	@ApiModelProperty(value = "原始梦境")
	private String originalDream;

	@ApiModelProperty(value = "梦境故事")
	private String generatedStory;

	@ApiModelProperty(value = "梦境含义")
	private String dreamMeaning;

	@ApiModelProperty(value = "")
	private Date createdAt;

	@ApiModelProperty(value = "")
	private Date updatedAt;

	@ApiModelProperty(value = "")
	private Integer isDeleted;


}