package com.hbhb.cw.jobexecutor.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaokang
 * @since 2020-10-29
 */
@Component
@Slf4j
public class TestXxlJob {

    @XxlJob(value = "demoJobHandler")
    public ReturnT<String> demoJobHandler(String s) throws Exception {
        for (int i = 0; i < 5; i++) {
            log.info("心跳日志index:" + i);
        }
        return ReturnT.SUCCESS;
    }
}
