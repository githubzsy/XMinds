package springcloud.controller;

import com.zhoushiya.springcloud.entities.CommonResult;
import com.zhoushiya.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhoushiya
 * @date 2020/9/26 18:39
 */
@RestController
@Slf4j
public class OrderZKController {
    /** 单机配置
     public static final String PAYMENT_URL = "http://localhost:8001";
     **/

    /**
     * 集群配置：http://微服务名称
     * zookeeper大小写敏感，注意大小写
     */
    private static final String PAYMENT_URL = "http://cloud-payment-service";

    private final RestTemplate restTemplate;

    public OrderZKController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/consumer/payment/zk")
    public String zk(Payment payment) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/zk", String.class);
    }
}
