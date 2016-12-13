package com.wabu.health.business.api.authorization;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.wabu.health.business.api.config.Constants;

/**
 * 通过Redis存储和验证token的实现类
 * @author yao
 *
 */
@Component
public class RedisTokenManager implements TokenManager {

	private RedisTemplate<String, String> redis;
	
	@Autowired
    public void setRedis(RedisTemplate<String, String> redis) {
        this.redis = redis;
        //泛型设置成Long后必须更改对应的序列化方案
        //redis.setKeySerializer(new JdkSerializationRedisSerializer());
    }
	
	@Override
	public Token create(String userId) {
		//使用uuid作为源token
        String token = UUID.randomUUID().toString().replace("-", "");
        Token model = new Token(userId, token);
        //存储到redis并设置过期时间
        redis.boundValueOps(userId).set(token, Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return model;
	}

	@Override
	public boolean check(Token token) {
		if (token == null) {
            return false;
        }
		
        String model = redis.boundValueOps(token.getUserId()).get();
        if (model == null || !model.equals(token.getToken())) {
            return false;
        }
        
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        redis.boundValueOps(token.getUserId()).expire(Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return true;
	}

	@Override
	public Token get(String authentication) {
		if (StringUtils.isBlank(authentication)) {
            return null;
        }
		
        String[] param = authentication.split("_");
        if (param.length != 2) {
            return null;
        }
        
        //使用userId和源token简单拼接成的token，可以增加加密措施
        String userId = param[0];
        String token = param[1];
        return new Token(userId, token);
	}

	@Override
	public void delete(String userId) {
		redis.delete(userId);
	}

}
