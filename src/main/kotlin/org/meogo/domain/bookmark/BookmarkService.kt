package org.meogo.domain.bookmark

import org.meogo.domain.post.domain.PostRepository
import org.meogo.domain.user.exception.UserNotFoundException
import org.meogo.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookmarkService(
    private val bookmarkRepository: BookmarkRepository,
    private val userFacade: UserFacade,
    private val postRepository: PostRepository
) {

    @Transactional
    fun execute(schoolId: Int) {
        val user = userFacade.currentUser() ?: throw UserNotFoundException

        bookmarkRepository.save(
            Bookmark(
                schoolId = schoolId,
                user = user
            )
        )
    }

    fun queryBookmarkedPost(schoolId: Int): Int {
        val posts = bookmarkRepository.findAllBySchoolId(schoolId)
        return posts?.size ?: 0
    }

    fun queryIsBookmarked(schoolId: Int): Boolean {
        val user = userFacade.currentUser() ?: throw UserNotFoundException
        return bookmarkRepository.existsBySchoolIdAndUser(schoolId, user)
    }

    fun deleteBookmark(schoolId: Int) {
        val user = userFacade.currentUser() ?: throw UserNotFoundException
        bookmarkRepository.deleteBySchoolIdAndUser(schoolId, user)
    }
}
