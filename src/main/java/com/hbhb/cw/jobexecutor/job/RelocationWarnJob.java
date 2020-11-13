package com.hbhb.cw.jobexecutor.job;


import com.hbhb.cw.jobexecutor.rpc.RelocationWarpApiExp;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wangxiaogang
 */
@Component
@Slf4j
public class RelocationWarnJob {

    @Resource
    private RelocationWarpApiExp relocationApi;

    @XxlJob(value = "RelocationWarn")
    public ReturnT<String> relocationWarn(String s) throws Exception {
        log.info("开始执行【预警信息同步】任务...");
        relocationApi.addWarn();
        log.info("【预警信息同步】任务执行完毕！");
        return ReturnT.SUCCESS;
    }
}
