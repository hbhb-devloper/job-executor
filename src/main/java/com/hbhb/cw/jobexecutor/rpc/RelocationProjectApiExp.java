package com.hbhb.cw.jobexecutor.rpc;

import com.hbhb.cw.relocationmange.api.RelocationProjectApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "${provider.relocation-manage}", url = "${feign-url}",contextId = "RelocationProjectApi" ,path = "/relocation")
public interface RelocationProjectApiExp extends RelocationProjectApi {
}
