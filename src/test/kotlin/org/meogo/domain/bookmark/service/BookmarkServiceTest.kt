package org.meogo.domain.bookmark.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.meogo.domain.bookmark.domain.Bookmark
import org.meogo.domain.bookmark.domain.BookmarkRepository
import org.meogo.domain.bookmark.exception.BookmarkNotFoundException
import org.meogo.domain.user.domain.User
import org.meogo.domain.user.domain.UserRole
import org.meogo.domain.user.facade.UserFacade
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest
@ExtendWith(SpringExtension::class)
@ActiveProfiles("test")
class BookmarkServiceTest {

    @Mock
    private lateinit var bookmarkRepository: BookmarkRepository

    @Mock
    private lateinit var userFacade: UserFacade

    @InjectMocks
    private lateinit var bookmarkService: BookmarkService

    private lateinit var user: User
    private val schoolId = 123

    @BeforeEach
    fun setUp() {
        user = User(
            name = "Test User",
            accountId = "test",
            password = "password",
            role = UserRole.USER,
            profile = "default"
        )

        whenever(userFacade.currentUser()).thenReturn(user)
    }

    @Test
    fun `북마크 저장`() {
        // given & then
        bookmarkService.execute(schoolId)

        // then
        verify(bookmarkRepository).save(any())
    }

    @Test
    fun `북마크 리스트 조회`() {
        // given
        val bookmark1 = Bookmark(schoolId = 1, user = user)
        val bookmark2 = Bookmark(schoolId = 2, user = user)

        whenever(bookmarkRepository.findAllByUser(user)).thenReturn(listOf(bookmark1, bookmark2))

        // when
        val bookmarkedSchools = bookmarkService.queryBookmarkedSchool()

        // then
        assertEquals(2, bookmarkedSchools.size)
        assertEquals(1, bookmarkedSchools[0])
        assertEquals(2, bookmarkedSchools[1])
    }

    @Test
    fun `북마크 여부 확인`() {
        // given
        val bookmark = Bookmark(schoolId = schoolId, user = user)
        whenever(bookmarkRepository.existsBySchoolIdAndUser(bookmark.schoolId, bookmark.user)).thenReturn(true)

        // when
        val isBookmarked = bookmarkService.queryIsBookmarked(schoolId)

        // then
        assertEquals(true, isBookmarked)
    }

    @Test
    fun `북마크 삭제 성공`() {
        // given
        val bookmark = Bookmark(schoolId = schoolId, user = user)

        whenever(bookmarkRepository.findBySchoolIdAndUser(schoolId, user)).thenReturn(bookmark)

        // when
        bookmarkService.deleteBookmark(schoolId)

        // then
        verify(bookmarkRepository).delete(bookmark)
    }

    @Test
    fun `북마크 삭제 실패`() {
        // given
        whenever(bookmarkRepository.findBySchoolIdAndUser(schoolId, user)).thenReturn(null)

        // when & then
        try {
            bookmarkService.deleteBookmark(schoolId)
        } catch (e: BookmarkNotFoundException) {
            assertNotNull(e)
        }
    }
}
