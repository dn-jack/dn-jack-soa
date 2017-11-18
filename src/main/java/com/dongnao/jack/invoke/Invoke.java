package com.dongnao.jack.invoke;

/** 
 * @Description 返回String，用json的方式进行通信 
 * @ClassName   Invoke 
 * @Date        2017年11月11日 下午10:17:19 
 * @Author      dn-jack 
 */

public interface Invoke {
    public String invoke(Invocation invocation) throws Exception;
}
