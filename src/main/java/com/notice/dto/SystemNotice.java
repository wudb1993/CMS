package com.notice.dto;

import java.util.Date;
public class SystemNotice {
    private String id ;
    private String noticeTitle;//标题
    private String noticeContont;//概述
    private String noticeArticle;//内容
    private String author;//作者
    private String status;//状态
    private Date insertTime;//创建时间
    private Date operaTime;//最后修改时间
    private Date operaAuthor;//审核人

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContont() {
        return noticeContont;
    }

    public void setNoticeContont(String noticeContont) {
        this.noticeContont = noticeContont;
    }

    public String getNoticeArticle() {
        return noticeArticle;
    }

    public void setNoticeArticle(String noticeArticle) {
        this.noticeArticle = noticeArticle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getOperaTime() {
        return operaTime;
    }

    public void setOperaTime(Date operaTime) {
        this.operaTime = operaTime;
    }

    public Date getOperaAuthor() {
        return operaAuthor;
    }

    public void setOperaAuthor(Date operaAuthor) {
        this.operaAuthor = operaAuthor;
    }
}
