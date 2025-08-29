package com.minhphung.taskmanagementsystem.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    private static final Duration TTL = Duration.ofMinutes(1);

    @Autowired
    public RedisService(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save (String username, List<String> permissions){
        redisTemplate.opsForValue().set("user:"+username,permissions, TTL);
    }

    public List<String> findPermissions(String username){
        return (List<String>) redisTemplate.opsForValue().get("user:"+username);
    }
}
