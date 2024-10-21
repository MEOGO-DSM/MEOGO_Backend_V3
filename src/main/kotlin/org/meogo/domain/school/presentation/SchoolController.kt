package org.meogo.domain.school.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.meogo.domain.school.enum.Gubun
import org.meogo.domain.school.service.QuerySchoolService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "School API")
@RestController
@RequestMapping("/school")
class SchoolController(
    private val querySchoolService: QuerySchoolService
) {

    @Operation(summary = "학교 정보 조회")
    @GetMapping
    fun getSchools(
        @RequestParam(name = "gubun")
        gubun: Gubun,
        @RequestParam(name = "region")
        region: String?,
        @RequestParam(name = "name")
        name: String?,
        @RequestParam(name = "sch1")
        sch1: String?
    ) = querySchoolService.execute(gubun, region, name, sch1)
}
