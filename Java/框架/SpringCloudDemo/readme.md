# Cloud项目说明
## 1.子项目命名规则：cloud-服务组件名称-角色-功能端口号
```
例如 cloud-consul-consumer-order86 表示服务注册组件是consul，角色是消费者，功能是订单相关，端口号是86。那么对应地址就是localhost:86
```

## 2.当前微服务只有两个
1. 消费者consumer-payment，统一别名为consumer-payment，与服务注册组件无关。示例路径为：```localhost:端口号/consumer/payment/uuid```
2. 提供者provider-order，统一别名为provider-order，与服务注册组件无关。示例路径为：```localhost:端口号/payment/uuid```
