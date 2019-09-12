es不合理，文章比较少

# 问题
1. buffer 和 filecache
2. node那么中类型，怎么说
3. 写入的流程是怎么样的

# 节点类型

ingest

node.master: true
node.data: true         
node.ingest: true        # 能执行预处理管道，有自己独立的任务要执行，类似于 logstash 的功能
node.ml: false    

# translog 也是异步的
默认5s，最小是100ms
# 
https://www.elastic.co/guide/en/elasticsearch/reference/current/index-modules-translog.html


https://www.elastic.co/pdf/architecture-best-practices.pdf

https://lalitvc.files.wordpress.com/2018/05/mysql_architecture_guide.pdf

https://www.infoq.cn/article/analysis-of-elasticsearch-cluster-part01

https://blog.insightdatascience.com/anatomy-of-an-elasticsearch-cluster-part-i-7ac9a13b05db