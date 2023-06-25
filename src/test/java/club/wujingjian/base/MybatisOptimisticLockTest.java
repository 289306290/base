package club.wujingjian.base;

import club.wujingjian.base.enums.EnumSex;
import club.wujingjian.base.mapper.ProductMapper;
import club.wujingjian.base.po.Product;
import club.wujingjian.base.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisOptimisticLockTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void testProduct1(){
        Product productLi = productMapper.selectById(1);
        System.out.println("小李查询的商品价格:" + productLi.getPrice());

        Product productWang = productMapper.selectById(1);
        System.out.println("小王查询的商品价格:" + productWang.getPrice());

        productLi.setPrice(productLi.getPrice() + 50);
        productMapper.updateById(productLi);

        productWang.setPrice(productWang.getPrice() + 50);
        productMapper.updateById(productWang);

        Product productBoss= productMapper.selectById(1);
        System.out.println(productBoss.getPrice());
    }



}
