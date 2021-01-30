package com.hbhb.cw.jobexecutor.job;

import com.hbhb.cw.jobexecutor.rpc.PublicityMaterialsApiExp;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @author wangxiaogang
 */
@Slf4j
public class PublicityMaterialsJob {
    @Resource
    private PublicityMaterialsApiExp materialsApiExp;

    @XxlJob(value = "publicityMaterialsBudget")
    public ReturnT<String> publicityMaterialsBudget(String s) throws Exception {
        log.info("开始执行【新增物料制作费用】任务...");
        materialsApiExp.saveBudget();
        log.info("【新增物料制作费用】任务执行完毕！");
        return ReturnT.SUCCESS;
    }
}
