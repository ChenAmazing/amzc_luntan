package com.mty.cisp.dao;

import com.mty.cisp.domain.Category;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by mty on 2019-02-16
 */
@Repository
@Mapper
public interface CategoryMapper {

  List<Category> getAllCategory();

  void create(Category category);

  void deleteById(Integer id);

  void updateById(Category category);

  Category queryById(Integer id);
}
