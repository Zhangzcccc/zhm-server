package com.ic.agile;

import com.ic.agile.service.DynamicDataSourceTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 多数据源测试
 *
 * @author zhangzc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicDataSourceTest {
    @Resource
    private DynamicDataSourceTestService dynamicDataSourceTestService;

    @Test
    public void test() {
        Long id = 1067246875800000001L;

        dynamicDataSourceTestService.updateUser(id);
        dynamicDataSourceTestService.updateUserBySlave1(id);
        //dynamicDataSourceTestService.updateUserBySlave2(id);
    }


}
