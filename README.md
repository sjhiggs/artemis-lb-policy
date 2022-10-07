# Introduction

This is a custom load balancer policy for Artemis, intended to be installed as a module in EAP for the resource adapter.

# Installation

Install the module in EAP
```
$ mvn clean install
$ unzip target/artemis-lb-policy-1.0-dist.zip
$ cp -r target/artemis-lb-policy-1.0/org ${EAP_HOME}/modules/
```

Modify EAP configuration (e.g. standalone-full.xml) to include the policy.  For example:
```
<pooled-connection-factory name="activemq-remote-ra" entries="java:/RemoteJmsXA java:jboss/DefaultJMSConnectionFactory java:jboss/RemoteJmsXA java:/jms/DefaultJMSConnectionFactory" connectors="netty-remote-throughput-node1 netty-remote-throughput-node2" ha="true" reconnect-attempts="-1" use-topology-for-load-balancing="false" transaction="xa" user="admin" password="admin" connection-load-balancing-policy-class-name="org.apache.activemq.artemis.api.core.client.loadbalance.OrderedLoadBalancingPolicy"  >
```

Note: as a workaround while an enhancement is in progress, the following also needs to be configured:

```
$ cat modules/system/layers/base/org/apache/activemq/artemis/journal/main/module.xml | grep addons
        <module name="org.apache.activemq.artemis.addons" optional="true" export="true"/>
```

