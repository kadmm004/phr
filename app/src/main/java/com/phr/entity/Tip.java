package com.phr.entity;

public class Tip {
    private String TipTitle;
    private String TipContent;


    public Tip(String tipTitle, String tipContent) {
        TipTitle = tipTitle;
        TipContent = tipContent;
    }

    public Tip(String tipTitle) {
        TipTitle = tipTitle;
    }

    public String getTipTitle() {
        return TipTitle;
    }

    public void setTipTitle(String tipTitle) {
        TipTitle = tipTitle;
    }

    public String getTipContent() {
        return TipContent;
    }

    public void setTipContent(String tipContent) {
        TipContent = tipContent;
    }
}
