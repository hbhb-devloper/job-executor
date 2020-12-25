package com.hbhb.cw.jobexecutor.job;

import com.hbhb.cw.jobexecutor.rpc.PublicitySettingApiExp;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yzc
 * @since 2020-12-25
 */
@Component
@Slf4j
public class PublicityProjectJpb {
    @Resource
    private PublicitySettingApiExp publicitySettingApiExp;

    @XxlJob(value = "PublicitySetting")
    public ReturnT<String> publicitySetting(String s) throws Exception {
        log.info("开始执行【更新基础信息未全额回款历时】任务...");
        publicitySettingApiExp.addGoodsSetting();
        log.info("【更新基础信息未全额回款历时】任务执行完毕！");
        return ReturnT.SUCCESS;
    }
}
