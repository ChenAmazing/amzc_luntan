package com.mty.cisp.service.impl;

import com.mty.cisp.dao.ArticleMapper;
import com.mty.cisp.dao.CommentMapper;
import com.mty.cisp.dao.UserMapper;
import com.mty.cisp.domain.Comment;
import com.mty.cisp.domain.User;
import com.mty.cisp.service.CommentService;
import com.mty.cisp.vo.ArticleVO;
import com.mty.cisp.vo.CommentVO;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * Created by mty on 2019-02-19
 */
@Service
public class CommentServiceImpl implements CommentService {

  @Resource
  CommentMapper commentMapper;

  @Resource
  UserMapper userMapper;

  @Resource
  ArticleMapper articleMapper;

  @Override
  public void create(Comment comment) {
    commentMapper.create(comment);
    articleMapper.addComment(comment.getArticleId());
  }

  @Override
  public List<CommentVO> getCommentByArticleId(Integer articleId) {
    List<CommentVO> commentVOList = commentMapper.getCommentByArticleId(articleId);
    for (CommentVO commentVO : commentVOList) {
      User user = userMapper.getUserById(commentVO.getUserId());
      commentVO.setAvatar(user.getAvatar());
      commentVO.setNickname(user.getNickname());
    }
    return commentVOList;
  }

  @Override
  public int countAllComment() {
    List<ArticleVO> articleVOList = articleMapper.getIndexArticles(null);
    int count = 0;
    for (ArticleVO articleVO : articleVOList) {
      count += this.getCommentByArticleId(articleVO.getId()).size();
    }
    return count;
  }

  @Override
  public void delete(Comment comment) {
    Comment tmpComment = commentMapper.getById(comment.getId());
    commentMapper.delete(comment);
    articleMapper.deleteComment(tmpComment.getArticleId());
  }
}
