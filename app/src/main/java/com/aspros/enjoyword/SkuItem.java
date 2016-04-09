package com.aspros.enjoyword;

/**
 * Created by Aspros on 16/4/3.
 */
public class SkuItem {

    private int nameLength;//商品属性名称的长度<br/>
    private int nameState;//商品状态
    private String nameVersion;
    private String nameType;
    private int nameNum;//库存
    private float namePrice;//价格

    public int getNameLength() {
        return nameLength;
    }

    public void setNameLength(int nameLength) {
        this.nameLength = nameLength;
    }

    public int getNameState() {
        return nameState;
    }

    public void setNameState(int nameState) {
        this.nameState = nameState;
    }

    public int getNameNum() {
        return nameNum;
    }

    public void setNameNum(int nameNum) {
        this.nameNum = nameNum;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public String getNameVersion() {
        return nameVersion;
    }

    public void setNameVersion(String nameVersion) {
        this.nameVersion = nameVersion;
    }

    public float getNamePrice() {
        return namePrice;
    }

    public void setNamePrice(float namePrice) {
        this.namePrice = namePrice;
    }
}