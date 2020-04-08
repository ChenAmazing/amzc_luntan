package com.mty.cisp.service;

import com.mty.cisp.domain.Category;
import java.util.List;

/**
 * Created by mty on 2019-02-16
 */
public interface CategoryService {

  List<Category> getAllCategory();

  void create(Category category);

  void deleteById(Integer id);

  void updateById(Category category);

  Category queryById(Integer id);
}
