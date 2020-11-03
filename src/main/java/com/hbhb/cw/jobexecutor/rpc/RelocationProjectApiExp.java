package com.hbhb.cw.jobexecutor.rpc;

import com.hbhb.cw.relocation.api.RelocationProjectApi;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "relocation-manage", contextId = "RelocationProjectApi", path = "/relocation")
public interface RelocationProjectApiExp extends RelocationProjectApi {
}
