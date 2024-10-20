package org.meogo.global.feign


data class CareerSchoolListResponse(
    val dataSearch: DataSearch
)

data class DataSearch(
    val content: List<CareerSchoolResponse>
)

data class CareerSchoolResponse(
    val seq: String,
    val link: String,
    val schoolGubun: String,
    val adres: String,
    val schoolName: String,
    val estType: String
)