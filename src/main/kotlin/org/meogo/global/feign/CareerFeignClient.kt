package org.meogo.global.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "career", url = "\${url.career}")
interface CareerFeignClient {

    @GetMapping("/getOpenApi")
    fun getCareerInfoToEntity(
        @RequestParam(name = FeignProperty.API_KEY) apiKey: String,
        @RequestParam(name = FeignProperty.SVC_TYPE) svcType: String,
        @RequestParam(name = FeignProperty.SVC_CODE) svcCode: String,
        @RequestParam(name = FeignProperty.GUBUN) gubun: String,
        @RequestParam(name = FeignProperty.CONTENT_TYPE) contentType: String,
        @RequestParam(name = "perPage") perPage: Int,
    ): String
}
