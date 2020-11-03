package com.hbhb.cw.jobexecutor.rpc;

import com.hbhb.cw.relocation.api.RelocationWarnApi;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "relocation-manage", contextId = "RelocationWarnApi", path = "/warn")
public interface RelocationWarpApiExp extends RelocationWarnApi {
}
