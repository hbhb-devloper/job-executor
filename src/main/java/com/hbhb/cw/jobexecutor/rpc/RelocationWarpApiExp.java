package com.hbhb.cw.jobexecutor.rpc;


import com.hbhb.cw.relocationmange.api.RelocationWarnApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "${provider.relocation-manage}", url = "${feign-url}",contextId = "RelocationWarnApi" ,path = "/warn")
public interface RelocationWarpApiExp extends RelocationWarnApi {
}
