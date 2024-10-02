package org.meogo.domain.good.service

import org.meogo.domain.good.domain.Good
import org.meogo.domain.good.domain.GoodRepository
import org.meogo.domain.post.domain.PostRepository
import org.meogo.domain.user.exception.UserNotFoundException
import org.meogo.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GoodService(
    private val goodRepository: GoodRepository,
    private val userFacade: UserFacade,
    private val postRepository: PostRepository
) {

    @Transactional
    fun addGood(postId: Long) {
        val user = userFacade.currentUser() ?: throw UserNotFoundException
        val post = postRepository.findById(postId)

        post.addGood()
        goodRepository.save(
            Good(
                post = post,
                user = user
            )
        )
    }

    @Transactional
    fun deleteGood(postId: Long) {
        val user = userFacade.currentUser() ?: throw UserNotFoundException
        val post = postRepository.findById(postId)
        val good = goodRepository.findByUserAndPost(user, post)

        post.deleteGood()
        goodRepository.delete(good)
    }
}
