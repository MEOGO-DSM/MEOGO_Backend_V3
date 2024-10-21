package org.meogo.domain.school.service

import org.meogo.domain.school.enum.Gubun
import org.meogo.global.feign.CareerFeignClientService
import org.springframework.stereotype.Service

@Service
class QuerySchoolService(
    private val careerFeignClientService: CareerFeignClientService
) {

    fun execute(gubun: Gubun, region: String?, name: String?, sch1: String?) =
        careerFeignClientService.getSchoolInfo(gubun, name, region, sch1).sortedBy { it.schoolName }
}
