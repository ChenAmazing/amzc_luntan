package com.mty.cisp.domain;

/**
 * Created by mty on 2019-02-19
 */

public class Comment {

  private Integer id;
  private Integer userId;
  private Integer articleId;
  private String content;
  private String createTime;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getArticleId() {
    return articleId;
  }

  public void setArticleId(Integer articleId) {
    this.articleId = articleId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  @Override
  public String toString() {
    return "Comment{" +
            "id=" + id +
            ", userId=" + userId +
            ", articleId=" + articleId +
            ", content='" + content + '\'' +
            ", createTime='" + createTime + '\'' +
            '}';
  }
}
