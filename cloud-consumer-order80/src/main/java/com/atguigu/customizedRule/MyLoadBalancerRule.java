package com.atguigu.customizedRule;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MyLoadBalancerRule implements IRule {

    private ILoadBalancer iLoadBalancer;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     *
     * @return next request index
     */
    public final int getAndIncrement(){
        int current;
        int next;
        do{
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        }while(!atomicInteger.compareAndSet(current, next));
        return next;
    }

    @Override
    public Server choose(Object o) {
        List<Server> serverList = iLoadBalancer.getAllServers();
        int serverIndex = getAndIncrement() % serverList.size();
        return serverList.get(serverIndex);
    }

    @Override
    public void setLoadBalancer(ILoadBalancer iLoadBalancer) {
        this.iLoadBalancer = iLoadBalancer;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return iLoadBalancer;
    }
}
