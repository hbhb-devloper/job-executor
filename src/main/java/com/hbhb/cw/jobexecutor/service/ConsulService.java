package com.hbhb.cw.jobexecutor.service;

import com.hbhb.core.utils.JsonUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaokang
 * @since 2020-10-30
 */
@Service
@Slf4j
public class ConsulService {

    @Value("${consul.list-critical-url}")
    private String listCriticalUrl;
    @Value("${consul.deregister-service-url}")
    private String deregisterServiceUrl;

    /**
     * 清除consul无效服务
     */
    public void deregisterCriticalService(String thisServiceId) {
        // 先查询无效服务serviceId
        String[] cmd1 = {"curl", "-X", "GET", listCriticalUrl};
        List<String> serviceIds = new ArrayList<>();
        serviceIds.add(thisServiceId);
        List<String> checkIds = JsonUtil.findByKeyFromArray(execCurl(cmd1), "CheckID");
        if (!CollectionUtils.isEmpty(checkIds)) {
            checkIds.forEach(checkId -> serviceIds.add(checkId.replace("\"", "").split(":")[1]));
        }

        // 开始清除
        if (!CollectionUtils.isEmpty(serviceIds)) {
            log.info("开始清除consul无效服务：{}", serviceIds);
            serviceIds.forEach(serviceId -> {
                String[] cmd2 = {"curl", "-X", "PUT", deregisterServiceUrl + serviceId};
                execCurl(cmd2);
            });
            log.info("consul无效服务全部清除完毕！");
        }
    }

    private static String execCurl(String[] cmd) {
        ProcessBuilder process = new ProcessBuilder(cmd);
        Process p;
        try {
            p = process.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            return builder.toString();
        } catch (Exception e) {
            log.error("执行curl命令失败:", e);
            e.printStackTrace();
        }
        return null;
    }
}
