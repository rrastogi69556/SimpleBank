package com.rabo.simpleBank.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler
import org.springframework.security.oauth2.provider.token.TokenStore

@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig : AuthorizationServerConfigurerAdapter() {
    @Autowired
    private val tokenStore: TokenStore? = null

    @Autowired
    private val userApprovalHandler: UserApprovalHandler? = null

    @Autowired
    private val authenticationManager: AuthenticationManager? = null

    @Throws(Exception::class)
    override fun configure(configurer: ClientDetailsServiceConfigurer) {
        configurer
            .inMemory()
            .withClient(CLIENT_ID)
            .secret(CLIENT_SECRET)
            .authorizedGrantTypes(GRANT_TYPE)
            .scopes(SCOPE_READ, SCOPE_WRITE)
            .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS).
            refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS)
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
            .authenticationManager(authenticationManager)
    }

    companion object {
        const val CLIENT_ID: String = "account-client"
        const val CLIENT_SECRET: String = "account-secret"
        const val GRANT_TYPE: String = "password"
        const val SCOPE_READ: String = "read"
        const val SCOPE_WRITE: String = "write"
        const val ACCESS_TOKEN_VALIDITY_SECONDS: Int = 60 * 60
        const val REFRESH_TOKEN_VALIDITY_SECONDS: Int = 6 * 60 * 60
    }
}