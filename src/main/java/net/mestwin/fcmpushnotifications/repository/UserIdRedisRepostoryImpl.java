//package net.mestwin.fcmpushnotifications.repository;
//
//import net.mestwin.fcmpushnotifications.entity.UserTokens;
//import org.springframework.data.redis.core.HashOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Repository;
//
//import javax.annotation.PostConstruct;
//
//@Repository
//public class UserIdRedisRepostoryImpl implements UserIdRedisRepository {
//
//    private static final String KEY = "User";
//
//    private RedisTemplate<String, Object> redisTemplate;
//    private HashOperations hashOperations;
//
//    public UserIdRedisRepostoryImpl(RedisTemplate<String, Object> redisTemplate){
//        this.redisTemplate = redisTemplate;
//    }
//
//    @PostConstruct
//    private void init(){
//        hashOperations = redisTemplate.opsForHash();
//    }
//
//    @Override
//    public UserTokens findTokens(String id) {
//
//        return (UserTokens) hashOperations.entries(KEY);
//
//    }
//
//    @Override
//    public void add(UserTokens userTokens) {
//        hashOperations.put(KEY, userTokens.getUserId(), userTokens.getFCMTokens());
//    }
//
//    @Override
//    public void delete(String id) {
//        hashOperations.delete(id);
//    }
//
//
//}
