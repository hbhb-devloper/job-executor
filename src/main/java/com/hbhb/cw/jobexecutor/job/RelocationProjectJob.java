package com.hbhb.cw.jobexecutor.job;

import com.hbhb.cw.jobexecutor.rpc.RelocationProjectApiExp;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class RelocationProjectJob {
    @Resource
    private RelocationProjectApiExp projectApiExp;

    @XxlJob(value = "RelocationProject")
    public ReturnT<String> RelocationProject() throws Exception {
        log.info("开始执行【更新基础信息未全额回款历时】任务...");
        projectApiExp.updateContractDuration();
        log.info("【更新基础信息未全额回款历时】任务执行完毕！");
        return ReturnT.SUCCESS;
    }
}
