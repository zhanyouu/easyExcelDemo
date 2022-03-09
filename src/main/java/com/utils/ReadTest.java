package com.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.fastjson.JSON;

import com.domain.entity.user.User;
import com.service.user.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 读的常见写法
 *
 * @author
 */
// 让 JUnit 运行 Spring 的测试环境， 获得 Spring 环境的上下文的支持
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ReadTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadTest.class);
    @Autowired
    private UserService userService;

    /**
     * 最简单的读
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link User}
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link }
     * <p>
     * 3. 直接读即可
     */
    @Test
    public void simpleRead() {
        System.out.println(this.userService.findById(1));
        // 写法1：JDK8+ ,不用额外写一个UserListener
        // since: 3.0.0-beta1
        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里每次会读取3000条数据 然后返回过来 直接调用使用数据就行
        EasyExcel.read(fileName, User.class, new PageReadListener<User>(dataList -> {
            for (User user : dataList) {
//                this.userService.insertUser(user);
                LOGGER.info("读取到一条数据{}", JSON.toJSONString(user));
            }
        })).sheet().doRead();

        // 写法2：
        // 匿名内部类 不用额外写一个UserListener
        fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, User.class, new ReadListener<User>() {
            /**
             * 单次缓存的数据量
             */
            public static final int BATCH_COUNT = 3000;
            /**
             *临时存储
             */
            private List<User> cachedData = new ArrayList<>(BATCH_COUNT);

            @Override
            public void invoke(User data, AnalysisContext context) {
                cachedData.add(data);
                if (cachedData.size() >= BATCH_COUNT) {
                    saveData();
                    // 存储完成清理 list
                    cachedData = new ArrayList<>(BATCH_COUNT);
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                saveData();
            }

            /**
             * 加上存储数据库
             */
            private void saveData() {
                LOGGER.info("{}条数据，开始存储数据库！", cachedData.size());
                for (User user: cachedData){
                    LOGGER.info(user.toString());
//                    userService.insertUser(user);
                }
                LOGGER.info("存储数据库成功！");
            }
        }).sheet().doRead();
    }
}
