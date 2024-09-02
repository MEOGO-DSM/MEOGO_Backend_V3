package org.meogo.global.auth

import org.meogo.domain.user.domain.UserRole
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AuthDetails(
    private val name: String,
    private val role: UserRole
) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return listOf<SimpleGrantedAuthority>(SimpleGrantedAuthority("ROLE_" + role.name))
    }

    override fun getPassword(): String? {
        return null
    }

    override fun getUsername(): String {
        return name
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
