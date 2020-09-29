package com.arrayparam.demo.form;

import java.util.List;

public class SkuForm {

    //goodsid
    private Long goodsId;
    public Long getGoodsId() {
        return this.goodsId;
    }
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }


    //type list
    private List<AttrType> typeList;
    public List<AttrType> getTypeList() {
        return this.typeList;
    }
    public void setTypeList(List<AttrType> typeList) {
        this.typeList = typeList;
    }


    //sku list
    private List<Sku> skuList;
    public List<Sku> getSkuList() {
        return this.skuList;
    }
    public void setSkuList(List<Sku> skuList) {
        this.skuList = skuList;
    }

    //file list
    private List<FormFile> fileList;
    public List<FormFile> getFileList() {
        return this.fileList;
    }
    public void setFileList(List<FormFile> fileList) {
        this.fileList = fileList;
    }
}
