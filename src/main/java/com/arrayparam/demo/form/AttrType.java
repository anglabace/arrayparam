package com.arrayparam.demo.form;

import java.util.List;

public class AttrType {
    //typename
    private String typeName;
    public String getTypeName() {
        return this.typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    //isImage
    private int isImage;
    public int getIsImage() {
        return this.isImage;
    }
    public void setIsImage(int isImage) {
        this.isImage = isImage;
    }

    //attr list
    private List<String> attrList;
    public List<String> getAttrList() {
        return this.attrList;
    }
    public void setAttrList(List<String> attrList) {
        this.attrList = attrList;
    }
}
