package org.meogo.domain.question.presentation

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
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createQuestion(
        @Valid @RequestBody
        request: QuestionRequest
    ) = createQuestionService.execute(request)

    @PatchMapping("/modify")
    fun modifyQuestion(
        @RequestParam(name = "question_id")
        questionId: Long,
        @Valid @RequestBody
        request: ModifyQuestionRequest
    ) =
        modifyQuestionService.execute(questionId, request)

    @GetMapping("/query")
    fun querySchoolQuestion(@RequestParam(name = "school_id")schoolId: Int) =
        querySchoolQuestionService.execute(schoolId)

    @GetMapping("/query/tag")
    fun queryTypeQuestion(
        @RequestParam(name = "school_id")schoolId: Int,
        @RequestParam(name = "tag") type: QuestionType
    ) = queryTypeQuestionService.execute(schoolId, type)

    @GetMapping("/detail")
    fun queryDetail(@RequestParam(name = "question_id")questionId: Long) =
        queryDetailQuestionService.execute(questionId)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping()
    fun deleteQuestion(@RequestParam(name = "question_id")questionId: Long) =
        deleteQuestionService.execute(questionId)
}
