package com.dongnao.jack.cluster;

import com.dongnao.jack.invoke.Invocation;

public interface Cluster {
    public String invoke(Invocation invocation) throws Exception;
}
