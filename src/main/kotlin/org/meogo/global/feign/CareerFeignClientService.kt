package org.meogo.global.feign

import com.google.gson.Gson
import org.meogo.domain.school.domain.School
import org.meogo.domain.school.enum.Gubun
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class CareerFeignClientService(
    @Value("\${open-feign.career-key}")
    private val careerKey: String,
    private val careerFeignClient: CareerFeignClient
) {
    fun getSchoolInfo(gubun: Gubun, schoolName: String?, region: String?, sch1: String?): List<School> {
        val schoolInfoSchool = careerFeignClient.getCareerInfoToEntity(
            apiKey = careerKey,
            svcType = FeignRequestProperty.SVC_TYPE,
            svcCode = FeignRequestProperty.SVC_CODE,
            gubun = gubun.value,
            contentType = FeignRequestProperty.CONTENT_TYPE,
            searchSchoolNm = schoolName,
            region = region,
            sch1 = sch1,
            perPage = FeignRequestProperty.PER_PAGE
        )

        val schoolInfoJson = Gson().fromJson(schoolInfoSchool, CareerSchoolListResponse::class.java)
        val schoolList = addToList(schoolInfoJson)
        return schoolList
    }

    fun addToList(schoolInfoJson: CareerSchoolListResponse): List<School> {
        val content = schoolInfoJson.dataSearch.content ?: return emptyList()
        return content.map { school ->
            School(
                id = school.seq.toInt(),
                link = school.link,
                schoolGubun = school.let { it.schoolGubun }.toString(),
                adres = school.adres,
                schoolName = school.schoolName
            )
        }
    }
}
