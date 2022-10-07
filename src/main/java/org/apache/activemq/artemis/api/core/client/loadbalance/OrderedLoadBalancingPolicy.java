package org.apache.activemq.artemis.api.core.client.loadbalance;

public class OrderedLoadBalancingPolicy implements ConnectionLoadBalancingPolicy {

    private int pos = -1;

    @Override
    public int select(final int max) {
        pos++;
        if (pos >= max) {
            pos = 0;
        }
        return pos;
    }
}
