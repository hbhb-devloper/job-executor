package com.hbhb.cw.jobexecutor.service;

import com.hbhb.core.utils.JsonUtil;

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

    private static final String LIST_CRITICAL_URL = "https://consul.yeexun.com.cn/v1/health/state/critical";
    private static final String DEREGISTER_SERVICE_URL = "https://consul.yeexun.com.cn/v1/agent/service/deregister/";

    /**
     * 清除consul无效服务
     */
    public void deregisterCriticalService() {
        // 先查询无效服务serviceId
        String[] cmd1 = {"curl", "-X", "GET", LIST_CRITICAL_URL};
        List<String> serviceIds = new ArrayList<>();
        List<String> checkIds = JsonUtil.findByKeyFromArray(execCurl(cmd1), "CheckID");
        if (!CollectionUtils.isEmpty(checkIds)) {
            checkIds.forEach(checkId -> serviceIds.add(checkId.replace("\"", "").split(":")[1]));
        }

        // 开始清除
        if (!CollectionUtils.isEmpty(serviceIds)) {
            log.info("开始清除consul无效服务：{}", serviceIds);
            serviceIds.forEach(serviceId -> {
                String[] cmd2 = {"curl", "-X", "PUT", DEREGISTER_SERVICE_URL + serviceId};
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
