package com.domain.entity.email;


import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.converters.string.StringStringConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ColumnWidth(22)
@HeadRowHeight(25)
@ContentRowHeight(16)
@ExcelIgnoreUnannotated
public class EmailInfoModel implements Serializable {
    @ExcelProperty(value = "emailId", converter = StringStringConverter.class)
    private String emailId;
}
