package com.mty.cisp.service.impl;

import com.mty.cisp.dao.UserMapper;
import com.mty.cisp.domain.User;
import com.mty.cisp.service.UserService;
//import com.mty.cisp.utils.PasswordUtil;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * Created by mty on 2019-02-13
 */
@Service
public class UserServiceImpl implements UserService {

  @Resource
  UserMapper userMapper;

  @Override
  public User loginCheck(User user) {
    User tmpUser = userMapper.selectByUsername(user.getUsername());
    if (tmpUser.getPassword().equals(user.getPassword())) {
      return tmpUser;
    } else {
      return null;
    }
  }

  @Override
  public void register(User user) {
    user.setPassword(user.getPassword());
    userMapper.insert(user);
  }

  @Override
  public Boolean isUsernameExsit(String username) {
    return userMapper.selectByUsername(username) != null;
  }

  @Override
  public User getUserByUsername(String username) {
    return userMapper.selectByUsername(username);
  }

  @Override
  public int countAll(String search) {
    return userMapper.countAll(search);
  }

  @Override
  public List<User> getUsers(String search) {
    return userMapper.getUsers(search);
  }

  @Override
  public void updateUser(User user) {
    if (user.getPassword() != null) {
      if ("".equals(user.getPassword())) {
        user.setPassword(null);
      } else {
        user.setPassword(user.getPassword());
      }
    }
    userMapper.updateUsers(user);
  }

  @Override
  public void updateUsers(User user) {
    if (user.getPassword() != null) {
      if ("".equals(user.getPassword())) {
        user.setPassword(null);
      } else {
        user.setPassword(user.getPassword());
      }
    }
    userMapper.updateUser(user);
  }

  @Override
  public User getUserById(Integer id) {
    return userMapper.getUserById(id);
  }

  @Override
  public void deleteById(Integer id) {
    userMapper.deleteById(id);
  }
}
