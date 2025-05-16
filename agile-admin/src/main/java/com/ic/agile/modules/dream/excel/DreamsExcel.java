package com.ic.agile.modules.dream.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 用户梦境
 *
 * @author zhangzc xxx
 * @since 1.0.0 2025-04-28
 */
@Data
public class DreamsExcel {
    @ExcelProperty(value = "id")
    private String id;
    @ExcelProperty(value = "用户id")
    private String userId;
    @ExcelProperty(value = "原始梦境")
    private String originalDream;
    @ExcelProperty(value = "梦境故事")
    private String generatedStory;
    @ExcelProperty(value = "梦境含义")
    private String dreamMeaning;
    @ExcelProperty(value = "")
    private Date createdAt;
    @ExcelProperty(value = "")
    private Date updatedAt;
    @ExcelProperty(value = "")
    private Integer isDeleted;

}