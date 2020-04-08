package com.mty.cisp.dao;

import com.mty.cisp.domain.User;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by mty on 2019-02-13
 */
@Mapper
@Repository
public interface UserMapper {

  User selectByUsername(String username);

  int insert(User user);

  int countAll(@Param("search") String search);

  List<User> getUsers(@Param("search") String search);

  void updateUser(User user);

  void updateUsers(User user);

  User getUserById(Integer id);

  void deleteById(Integer id);
}
