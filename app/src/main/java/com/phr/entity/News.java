package com.phr.entity;

public class News {
    private String NewsTitle;
    private String NewsContent;

    public News(String newsTitle) {
        NewsTitle = newsTitle;
    }
    public News(String newsTitle, String newsContent) {
        NewsTitle = newsTitle;
        NewsContent = newsContent;
    }
    public String getNewsTitle() {
        return NewsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        NewsTitle = newsTitle;
    }

    public String getNewsContent() {
        return NewsContent;
    }

}
