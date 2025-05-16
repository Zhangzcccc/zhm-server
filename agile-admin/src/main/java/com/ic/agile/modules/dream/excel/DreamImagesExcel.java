package com.ic.agile.modules.dream.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 梦境图片
 *
 * @author zhangzc xxx
 * @since 1.0.0 2025-04-28
 */
@Data
public class DreamImagesExcel {
    @ExcelProperty(value = "id")
    private String id;
    @ExcelProperty(value = "梦境id")
    private String dreamId;
    @ExcelProperty(value = "图片URL")
    private String imageUrl;
    @ExcelProperty(value = "排序")
    private Integer sortOrder;
    @ExcelProperty(value = "")
    private Date createdAt;

}