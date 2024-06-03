package com.vickllny.domain;

import com.baomidou.mybatisplus.annotation.TableField;

public class FieldVo {

    private String fieldName;
    private String column;
    private Object value;



    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(final String fieldName) {
        this.fieldName = fieldName;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(final String column) {
        this.column = column;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(final Object value) {
        this.value = value;
    }
}
