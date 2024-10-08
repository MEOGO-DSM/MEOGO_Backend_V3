package org.meogo.domain.post.service

import org.meogo.domain.post.domain.PostRepository
import org.meogo.domain.user.exception.UserMisMatchException
import org.meogo.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeletePostService(
    private val userFacade: UserFacade,
    private val postRepository: PostRepository
) {
    @Transactional
    fun execute(postId: Long) {
        val user = userFacade.currentUser()
        val post = postRepository.findById(postId)
        if (user!!.id != post.user.id) throw UserMisMatchException

        postRepository.deleteById(post.id)
    }
}
