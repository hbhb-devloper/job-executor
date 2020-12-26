package com.hbhb.cw.jobexecutor.rpc;

import com.hbhb.cw.publicity.api.PublicitySettingApi;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author yzc
 * @since 2020-12-25
 */
@FeignClient(value = "${provider.publicity-manage}", url = "${feign-url}",contextId = "PublicitySettingApi" ,path = "/goods/setting")
public interface PublicitySettingApiExp extends PublicitySettingApi {
}
