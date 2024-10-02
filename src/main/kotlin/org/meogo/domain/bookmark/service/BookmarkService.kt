package org.meogo.domain.bookmark.service

import org.meogo.domain.bookmark.domain.Bookmark
import org.meogo.domain.bookmark.domain.BookmarkRepository
import org.meogo.domain.bookmark.exception.BookmarkNotFoundException
import org.meogo.domain.post.domain.PostRepository
import org.meogo.domain.user.exception.UserNotFoundException
import org.meogo.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class BookmarkService(
    private val bookmarkRepository: BookmarkRepository,
    private val userFacade: UserFacade,
    private val postRepository: PostRepository
) {

    fun execute(schoolId: Int) {
        val user = userFacade.currentUser() ?: throw UserNotFoundException

        bookmarkRepository.save(
            Bookmark(
                schoolId = schoolId,
                user = user
            )
        )
    }

    fun queryBookmarkedSchool(): List<Int> {
        val user = userFacade.currentUser() ?: throw UserNotFoundException

        val posts = bookmarkRepository.findAllByUser(user)
        return posts?.map { it.schoolId } ?: emptyList()
    }

    fun queryIsBookmarked(schoolId: Int): Boolean {
        val user = userFacade.currentUser() ?: throw UserNotFoundException
        return bookmarkRepository.existsBySchoolIdAndUser(schoolId, user)
    }

    fun deleteBookmark(schoolId: Int) {
        val user = userFacade.currentUser() ?: throw UserNotFoundException
        val bookmark = bookmarkRepository.findBySchoolIdAndUser(schoolId, user) ?: throw BookmarkNotFoundException
        bookmarkRepository.delete(bookmark)
    }
}
