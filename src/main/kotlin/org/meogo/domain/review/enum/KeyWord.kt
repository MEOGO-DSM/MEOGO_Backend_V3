package org.meogo.domain.review.enum

enum class KeyWord(val keyword: String, val category: KeyWordCategory) {
    // Education
    ALL("전체 만족", KeyWordCategory.EDUCATION),
    GOOD_TEACHER("좋은 선생님", KeyWordCategory.EDUCATION),
    EDUCATIONAL_ACTIVITY("교육 활동", KeyWordCategory.EDUCATION),
    LEARNING_SUPPORT("학습 지원", KeyWordCategory.EDUCATION),
    ELECTIVE_SUBJECT("선택 과목", KeyWordCategory.EDUCATION),
    EXAM("시험", KeyWordCategory.EDUCATION),
    PERFORMANCE_EVALUATION("수행 평가", KeyWordCategory.EDUCATION),
    ADVANCED_LEARNING("심화 학습", KeyWordCategory.EDUCATION),
    LEARNING_MANAGEMENT("학습 관리", KeyWordCategory.EDUCATION),
    PROFESSIONALISM("전문성", KeyWordCategory.EDUCATION),
    REGULAR_ADMISSION("정시", KeyWordCategory.EDUCATION),
    SPECIAL_ADMISSION("수시", KeyWordCategory.EDUCATION),
    STUDENT_RECORD("생기부", KeyWordCategory.EDUCATION),

    // Activity
    IN_SCHOOL_ACTIVITY("교내 활동", KeyWordCategory.ACTIVITY),
    SCHOOL_EVENT("학교 행사", KeyWordCategory.ACTIVITY),
    CLUB_ACTIVITY("동아리 활동", KeyWordCategory.ACTIVITY),
    AFTER_SCHOOL_ACTIVITY("방과후 활동", KeyWordCategory.ACTIVITY),
    STUDENT_PARTICIPATION("학생 참여", KeyWordCategory.ACTIVITY),
    ARTS_AND_SPORTS_ACTIVITY("예체능 활동", KeyWordCategory.ACTIVITY),
    OUTSIDE_ACTIVITY("교외 활동", KeyWordCategory.ACTIVITY),
    SELF_GOVERNANCE_ACTIVITY("자치 활동", KeyWordCategory.ACTIVITY),

    // Facility
    ON_CAMPUS_FACILITY("교내 시설", KeyWordCategory.FACILITY),
    SAFE_ENVIRONMENT("안전한 환경", KeyWordCategory.FACILITY),
    CONVENIENT_TRANSPORT("교통 편리", KeyWordCategory.FACILITY),
    LEARNING_ATMOSPHERE("학습 분위기", KeyWordCategory.FACILITY),
    FRIENDSHIP_RELATIONS("교우 관계", KeyWordCategory.FACILITY),
    FACILITY_MANAGEMENT("시설 관리", KeyWordCategory.FACILITY)
}

enum class KeyWordCategory {
    EDUCATION, ACTIVITY, FACILITY
}
