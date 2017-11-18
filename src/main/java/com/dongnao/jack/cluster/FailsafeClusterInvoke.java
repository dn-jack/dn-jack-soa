package com.dongnao.jack.cluster;

import com.dongnao.jack.invoke.Invocation;
import com.dongnao.jack.invoke.Invoke;

/** 
 * @Description 调用节点失败，直接忽略 
 * @ClassName   FailsafeClusterInvoke 
 * @Date        2017年11月18日 下午9:55:49 
 * @Author      dn-jack 
 */

public class FailsafeClusterInvoke implements Cluster {
    
    public String invoke(Invocation invocation) throws Exception {
        Invoke invoke = invocation.getInvoke();
        
        try {
            return invoke.invoke(invocation);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "忽略";
        }
    }
    
}
