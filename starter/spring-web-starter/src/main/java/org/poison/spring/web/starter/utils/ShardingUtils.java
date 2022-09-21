package org.poison.spring.web.starter.utils;

public class ShardingUtils {
    public static int getShardingNum(String shardingKey,int shardingNum){
        if (shardingKey.equals("abc")){
            return 0;
        }else {
            return 1;
        }
    }
}
