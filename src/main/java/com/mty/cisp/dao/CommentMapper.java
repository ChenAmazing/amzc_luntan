package com.mty.cisp.dao;

import com.mty.cisp.domain.Comment;
import com.mty.cisp.vo.CommentVO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by mty on 2019-02-19
 */
@Repository
@Mapper
public interface CommentMapper {

  void create(Comment comment);

  List<CommentVO> getCommentByArticleId(Integer articleId);

  void delete(Comment comment);

  Comment getById(Integer id);

  void deleteByArticleId(Integer articleId);
}
