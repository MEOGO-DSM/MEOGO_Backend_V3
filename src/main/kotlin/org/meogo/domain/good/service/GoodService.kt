package org.meogo.domain.good.service

import org.meogo.domain.good.domain.Good
import org.meogo.domain.good.domain.GoodRepository
import org.meogo.domain.good.exception.AlreadyGoodException
import org.meogo.domain.good.exception.GoodNotFoundException
import org.meogo.domain.good.presentation.dto.response.MyGoodResponse
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
        if (goodRepository.existsByUserAndPost(user, post)) throw AlreadyGoodException

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
        val good = goodRepository.findByUserAndPost(user, post) ?: throw GoodNotFoundException

        post.deleteGood()
        goodRepository.delete(good)
    }

    fun queryMyGoods(): List<MyGoodResponse> {
        val user = userFacade.currentUser() ?: throw UserNotFoundException
        val posts = goodRepository.findAllByUser(user) ?: return emptyList()

        return posts.map { it ->
            val post = postRepository.findById(it.post.id)
            MyGoodResponse(
                postId = post.id,
                title = post.title,
                name = "익명",
                good = post.good,
                date = post.format(post.date),
                schoolId = post.schoolId
            )
        }
    }
}
