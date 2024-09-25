package org.meogo.domain.question.presentation

import lombok.RequiredArgsConstructor
import org.meogo.domain.question.presentation.dto.request.QuestionRequest
import org.meogo.domain.question.service.CreateQuestionService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RequiredArgsConstructor
@RestController
@RequestMapping("/question")
class QuestionController(
    private val createQuestionService: CreateQuestionService
) {
    @PostMapping
    fun createQuestion(
        @Valid @RequestBody
        request: QuestionRequest
    ) = createQuestionService.execute(request)
}
