package jedis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author dzq
 * @date 2021/10/21 10:30 上午
 **/
public class JedisTest {

    private Jedis jedis;

    @Before
    public void setJedis() {
        jedis = new Jedis("localhost", 6379);
    }

    @Test
    public void testString() {
        // 添加数据
        jedis.set("name", "Jack");
        System.out.println(jedis.get("name"));

        jedis.append("name", " rose");
        System.out.println("拼接后：" + jedis.get("name"));

        jedis.del("name");
        System.out.println("删除key：" + jedis.get("name"));

        // 设置多键值对
        jedis.mset("name", "lily", "age", "10", "gender", "male");
        jedis.incr("age");
        System.out.println(jedis.get("name") + " " + jedis.get("age") + " " + jedis.get("gender"));
    }

    @Test
    public void testMap() {
        //添加数据
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "chx");
        map.put("age", "100");
        map.put("email", "***@outlook.com");
        jedis.hmset("user", map);

        //取出user中的name，结果是一个泛型的List
        //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key是可变参数
        List<String> list = jedis.hmget("user", "name", "age", "email");
        System.out.println(list);

        // 删除map中key
        jedis.hdel("user", "age");
        System.out.println("age:" + jedis.hmget("user", "age")); //因为删除了，所以返回的是null
        System.out.println("user的键中存放的值的个数:" + jedis.hlen("user")); //返回key为user的键中存放的值的个数2
        System.out.println("是否存在key为user的记录:" + jedis.exists("user"));//是否存在key为user的记录 返回true
        System.out.println("user对象中的所有key:" + jedis.hkeys("user"));//返回user对象中的所有key
        System.out.println("user对象中的所有value:" + jedis.hvals("user"));//返回map对象中的所有value

        //拿到key，再通过迭代器得到值
        Iterator<String> iterator = jedis.hkeys("user").iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            System.out.println(key + ":" + jedis.hmget("user", key));
        }
        jedis.del("user");
        System.out.println("删除后是否存在key为user的记录:" + jedis.exists("user"));//是否存在key为user的记录
    }

    @Test
    public void testList() {
        jedis.lpush("javaFramework", "spring");
        jedis.lpush("javaFramework", "springMVC");
        jedis.lpush("javaFramework", "mybatis");
        //取出所有数据,jedis.lrange是按范围取出
        //第一个是key，第二个是起始位置，第三个是结束位置
        System.out.println("长度:" + jedis.llen("javaFramework"));
        //jedis.llen获取长度，-1表示取得所有
        System.out.println("javaFramework:" + jedis.lrange("javaFramework", 0, -1));

        jedis.del("javaFramework");
        System.out.println("删除后长度:" + jedis.llen("javaFramework"));
        System.out.println(jedis.lrange("javaFramework", 0, -1));
    }

    @Test
    public void testSet() {
        jedis.sadd("user", "1", "2", "3", "5");
        System.out.println(jedis.smembers("user"));

        //移除user集合中的元素
        jedis.srem("user", "2");
        System.out.println("user中的value:" + jedis.smembers("user"));//获取所有加入user的value
        System.out.println("2是否是user中的元素:" + jedis.sismember("user", "2"));//判断chx是否是user集合中的元素
        System.out.println("集合中的一个随机元素:" + jedis.srandmember("user"));//返回集合中的一个随机元素
        System.out.println("user中元素的个数:" + jedis.scard("user"));
    }
}
