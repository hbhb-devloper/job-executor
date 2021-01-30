package com.hbhb.cw.jobexecutor.rpc;

import com.hbhb.cw.publicitymanage.api.MaterialsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author wangxiaogang
 */
@FeignClient(value = "${provider.publicity-manage}", url = "${feign-url}",contextId = "MaterialsApi" ,path = "/budget-year")
public interface PublicityMaterialsApiExp extends MaterialsApi {
}
