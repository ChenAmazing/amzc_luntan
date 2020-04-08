package com.mty.cisp.service;

import com.mty.cisp.domain.Comment;
import com.mty.cisp.vo.CommentVO;
import java.util.List;

/**
 * Created by mty on 2019-02-19
 */

public interface CommentService {

  void create(Comment comment);

  List<CommentVO> getCommentByArticleId(Integer articleId);

  int countAllComment();

  void delete(Comment comment);
}
