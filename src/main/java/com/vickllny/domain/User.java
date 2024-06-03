package com.vickllny.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

public class User {

    private final HashMap<String, FieldVo> orgFieldMap = new HashMap<>();

    protected String sUserName;

    protected String sPassword;

    public Object virtualMethod(final String methodName, final Object params){
        if(methodName.startsWith("set")){
            final String fieldName = StringUtils.uncapitalize(methodName.replace("set", ""));
            return null;
        }else {
            final String fieldName = StringUtils.uncapitalize(methodName.replace("get", ""));
            return doGet(fieldName);
        }
    }

    private Object doGet(final String fieldName){
        FieldVo fieldVo;
        if((fieldVo = orgFieldMap.get(fieldName)) == null){
            return null;
        }
        return fieldVo.getValue();
    }

    private void doSet(final String fieldName, final Object value){
        orgFieldMap.computeIfAbsent(fieldName, v -> new FieldVo()).setValue(value);
    }

    public HashMap<String, FieldVo> getOrgFieldMap() {
        return orgFieldMap;
    }

    public String getsUserName() {
        return sUserName;
    }

    public void setsUserName(final String sUserName) {
        this.sUserName = sUserName;
    }

    public String getsPassword() {
        return sPassword;
    }

    public void setsPassword(final String sPassword) {
        this.sPassword = sPassword;
    }
}
