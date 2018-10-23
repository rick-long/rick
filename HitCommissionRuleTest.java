package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.spa.model.commission.CommissionRule;
import org.spa.model.product.ProductOption;
import org.spa.service.commission.CommissionRuleService;
import org.spa.service.product.ProductOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml",
                                "classpath:spring-hibernate.xml",
                                "classpath:spring-mvc.xml",
                                 "classpath:spring-shiro.xml"})
@WebAppConfiguration
@Transactional
public class HitCommissionRuleTest {

    @Autowired
    private CommissionRuleService commissionRuleService;

    @Autowired
    private ProductOptionService productOptionService;


    @Test
    public void test(){
        ProductOption productOption = productOptionService.get(6368l);
        CommissionRule commissionRule = commissionRuleService.hitCommissionRule(productOption,1l);
        System.out.println("commission:"+commissionRule);
    }

}
