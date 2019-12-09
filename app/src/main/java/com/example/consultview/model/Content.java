
package com.example.consultview.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("isHtml")
    @Expose
    private Boolean isHtml;
    //    @SerializedName("indentLevel")
//    @Expose
//    private Integer indentLevel;
    @SerializedName("align")
    @Expose
    private String align;
    @SerializedName("isTitle")
    @Expose
    private Boolean isTitle;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getIsHtml() {
        return isHtml;
    }

    public void setIsHtml(Boolean isHtml) {
        this.isHtml = isHtml;
    }

    //    public Integer getIndentLevel() {
//        return indentLevel;
//    }
//
//    public void setIndentLevel(Integer indentLevel) {
//        this.indentLevel = indentLevel;
//    }
    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public Boolean getIsTitle() {
        return isTitle;
    }

    public void setIsTitle(Boolean isTitle) {
        this.isTitle = isTitle;
    }

}
