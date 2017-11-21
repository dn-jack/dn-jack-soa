package com.dongnao.jack.redis;

import redis.clients.jedis.JedisPubSub;

/** 
 * @Description redis的发布与订阅，跟我们的activemq中的topic消息消费机制差不多
 * 是一个广播形式的消费消息 
 * @ClassName   RedisServerRegistry 
 * @Date        2017年11月18日 下午10:10:48 
 * @Author      dn-jack 
 */

public class RedisServerRegistry extends JedisPubSub {
    
    /* 
     * @see 当往频道其实就是队列，当往里面发布消息的时候，这个方法就会触发
     */
    @Override
    public void onMessage(String channel, String message) {
        
    }
    
    @Override
    public void subscribe(String... channels) {
        // TODO Auto-generated method stub
        super.subscribe(channels);
    }
    
}
