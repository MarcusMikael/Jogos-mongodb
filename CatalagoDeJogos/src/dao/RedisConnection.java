/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.UnifiedJedis;

public class RedisConnection {

    private static UnifiedJedis jedis;
// Conexão com o redis

    public static UnifiedJedis getConnection() {
        if (jedis == null) {
            JedisClientConfig config = DefaultJedisClientConfig.builder()
                    .user("default")
                    .password("dtxWHQJQA6YIxAwh9lrlKYWXJ1NkKUCA") // senha do Redis Cloud
                    .build();

            jedis = new UnifiedJedis(
                    new HostAndPort("redis-18707.crce216.sa-east-1-2.ec2.redns.redis-cloud.com", 18707),
                    config
            );

            System.out.println("Conectado ao Redis Cloud!");
        }
        return jedis;
    }

    public static void close() {
        if (jedis != null) {
            jedis.close();
        }
    }
}
