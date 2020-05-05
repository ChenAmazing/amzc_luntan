package com.zbin.cisp;

import com.mty.cisp.utils.JedisAdapter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CispApplicationTests.class)
public class CispApplicationTests {

  @Test
  public void contextLoads() {
//    System.out.println(FileUtil.delete("d3054dbf-75bf-4d62-9a92-29007e151308.jpg"));
    JedisPool pool = new JedisPool("192.168.142.102", 6379);
    Jedis jedis = null;
    try{
      jedis = pool.getResource();
      String ping = jedis.ping();
      System.out.println(ping+"pingpong连接测试!!!");
    }catch (Exception e){
      System.out.println("=====异常=====");
    }finally {
      jedis.close();
    }
  }

}

