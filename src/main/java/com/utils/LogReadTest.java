package com.utils;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;
import com.domain.entity.email.EmailInfoModel;
import com.domain.entity.email.Log;
import com.domain.entity.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class LogReadTest {
    private static final Logger logger = LoggerFactory.getLogger(ReadTest.class);

    @Test
    public void logTest(){
        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "email.csv";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里每次会读取3000条数据 然后返回过来 直接调用使用数据就行
        EasyExcel.read(fileName, Log.class, new PageReadListener<Log>(dataList -> {
            for (Log log : dataList) {
                logger.info("读取到一条数据{}", log.getLog());
            }
        })).sheet().doRead();
    }

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("\\b[A-Fa-f0-9]{32}\\b");
        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "email.csv";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里每次会读取3000条数据 然后返回过来 直接调用使用数据就行
        List<EmailInfoModel> emailInfoModelList = new ArrayList<>();
        EasyExcel.read(fileName, Log.class, new PageReadListener<Log>(dataList -> {
            for (Log log : dataList) {
                Matcher matcher = pattern.matcher(log.getLog());
                while (matcher.find()) {
                    EmailInfoModel emailInfoModel = new EmailInfoModel();
                    emailInfoModel.setEmailId(matcher.group());
                    emailInfoModelList.add(emailInfoModel);
                    logger.info("读取到一条数据{}", matcher.group());
                }
            }
            ExcelWriter excelWriter = EasyExcel.write(TestFileUtil.getPath() + "demo" + File.separator+"emailInfo.xlsx", EmailInfoModel.class).build();
            WriteSheet sheet = EasyExcel.writerSheet(1, "emailInfo").build();
            excelWriter.write(emailInfoModelList, sheet);
            excelWriter.finish();
        })).sheet().doRead();
    }
}
