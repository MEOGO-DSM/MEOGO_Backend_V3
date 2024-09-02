package org.meogo.global.jwt.entity

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class RefreshToken(
    @Id
    val id: String,

    var token: String,

    var refreshExp: Long

) {
    fun update(token: String?, ttl: Long) {
        this.token = token!!
        this.refreshExp = ttl
    }
}
