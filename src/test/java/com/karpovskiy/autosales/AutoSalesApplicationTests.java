package com.karpovskiy.autosales;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AutoSalesApplicationTests {

    @Test
    void contextLoads() {

    }

    @Test
    public void test(){
        int x = 2;
        int y = 23;

        Assertions.assertEquals(46, x * y);
    }

}
