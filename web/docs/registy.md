【已完成】海量数据下的注册中心 -
SOFARegistry 架构介绍

为什么SOFA 將注册中心设计为AP，而不是CP

前提是海量的注册服务，单机没法存储，必须要分片

# 分片范围不固定，如何进行数据复制
N1负责分片P1,P2，保存了P1，P2的操作日志，但是现在P1没了，重新分片，操作日志没用了，如何处理


- SOFARegistry 分片存储的实现详解
- SOFARegistry 数据推送机制详解
- SOFARegistry Meta 实现剖析
- SOFARegistry 最终一致性详解


# https://juejin.im/post/5cc2b2d46fb9a0324e4a3e89



