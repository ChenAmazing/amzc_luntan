package com.mty.cisp.service;

import com.mty.cisp.domain.User;
import java.util.List;

public interface UserService {

  /**
   * 登录校验
   */
  User loginCheck(User user);

  /**
   * 用户注册
   */
  void register(User user);

  /**
   * 用户名校验
   */
  Boolean isUsernameExsit(String username);

  User getUserByUsername(String username);

  int countAll(String search);

  List<User> getUsers(String search);

  void updateUser(User user);

  void updateUsers(User user);

  User getUserById(Integer id);

  void deleteById(Integer id);
}
