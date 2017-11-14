package com.dongnao.jack.invoke;

import java.util.List;

import com.dongnao.jack.configBean.Reference;
import com.dongnao.jack.loadbalance.LoadBalance;
import com.dongnao.jack.loadbalance.NodeInfo;

/** 
 * @Description 这个是http的调用过程 
 * @ClassName   HttpInvoke 
 * @Date        2017年11月14日 下午10:10:44 
 * @Author      dn-jack 
 */

public class HttpInvoke implements Invoke {
    
    public String invoke(Invocation invocation) {
        List<String> registryInfo = invocation.getReference().getRegistryInfo();
        //这个是负载均衡算法
        String loadbalance = invocation.getReference().getLoadbalance();
        Reference reference = invocation.getReference();
        LoadBalance loadbalanceBean = reference.getLoadBalances()
                .get(loadbalance);
        
        NodeInfo nodeinfo = loadbalanceBean.doSelect(registryInfo);
        return null;
    }
    
}
