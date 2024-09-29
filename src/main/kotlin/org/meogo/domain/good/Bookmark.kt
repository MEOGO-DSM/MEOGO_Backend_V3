package org.meogo.domain.good

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import org.meogo.domain.post.domain.Post
import org.meogo.domain.user.domain.User
import org.meogo.global.base.BaseUUIDEntity

@Entity
class Bookmark (
    id: UUID? = null,


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = true)
    val post: Post? = null,


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User

) : BaseUUIDEntity(id)