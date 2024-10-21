package org.meogo.domain.question.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.meogo.domain.question.enum.QuestionType
import org.meogo.domain.question.presentation.dto.request.ModifyQuestionRequest
import org.meogo.domain.question.presentation.dto.request.QuestionRequest
import org.meogo.domain.question.service.CreateQuestionService
import org.meogo.domain.question.service.DeleteQuestionService
import org.meogo.domain.question.service.ModifyQuestionService
import org.meogo.domain.question.service.QueryDetailQuestionService
import org.meogo.domain.question.service.QuerySchoolQuestionService
import org.meogo.domain.question.service.QueryTypeQuestionService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Tag(name = "Question API")
@RestController
@RequestMapping("/question")
class QuestionController(
    private val createQuestionService: CreateQuestionService,
    private val querySchoolQuestionService: QuerySchoolQuestionService,
    private val queryTypeQuestionService: QueryTypeQuestionService,
    private val queryDetailQuestionService: QueryDetailQuestionService,
    private val modifyQuestionService: ModifyQuestionService,
    private val deleteQuestionService: DeleteQuestionService
) {
    @Operation(summary = "질문 작성")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createQuestion(
        @Valid @RequestBody
        request: QuestionRequest
    ) = createQuestionService.execute(request)

    @Operation(summary = "질문 수정")
    @PatchMapping("/modify")
    fun modifyQuestion(
        @RequestParam(name = "question_id")
        questionId: Long,
        @Valid @RequestBody
        request: ModifyQuestionRequest
    ) =
        modifyQuestionService.execute(questionId, request)

    @Operation(summary = "학교별 질문 조회")
    @GetMapping("/query")
    fun querySchoolQuestion(@RequestParam(name = "school_id")schoolId: Int) =
        querySchoolQuestionService.execute(schoolId)

    @Operation(summary = "학교별 + 태그별 질문 조회")
    @GetMapping("/query/tag")
    fun queryTypeQuestion(
        @RequestParam(name = "school_id")schoolId: Int,
        @RequestParam(name = "tag") type: QuestionType
    ) = queryTypeQuestionService.execute(schoolId, type)

    @Operation(summary = "질문 상세보기")
    @GetMapping("/detail")
    fun queryDetail(@RequestParam(name = "question_id")questionId: Long) =
        queryDetailQuestionService.execute(questionId)

    @Operation(summary = "질문 삭제")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    fun deleteQuestion(@RequestParam(name = "question_id")questionId: Long) =
        deleteQuestionService.execute(questionId)
}
