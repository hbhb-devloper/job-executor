package com.hbhb.cw.jobexecutor.rpc;


import com.hbhb.cw.relocation.api.RelocationWarnApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author wangxiaogang
 */
@FeignClient(value = "${provider.relocation-manage}", url = "${feign-url}",contextId = "RelocationWarnApi" ,path = "/warn")
public interface RelocationWarpApiExp extends RelocationWarnApi {
}
