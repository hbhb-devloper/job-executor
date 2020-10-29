package com.hbhb.cw.jobexecutor.job;

import com.hbhb.cw.jobexecutor.service.ConsulService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaokang
 * @since 2020-10-29
 */
@Component
@Slf4j
public class ConsulJob {

    @Resource
    private ConsulService consulService;

    @XxlJob(value = "consulDeregisterService")
    public ReturnT<String> consulDeregisterService() {
        log.info("开始执行【清除consul无效服务】任务...");
        consulService.deregisterCriticalService();
        log.info("【清除consul无效服务】任务执行完毕！");
        return ReturnT.SUCCESS;
    }
}