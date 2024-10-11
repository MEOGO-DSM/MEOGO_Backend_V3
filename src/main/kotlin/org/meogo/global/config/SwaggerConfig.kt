package org.meogo.global.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class SwaggerConfig : WebMvcConfigurer {
    @Bean
    fun openApi(): OpenAPI {
        return OpenAPI()
            .info(
                Info().title("MEOGO API")
                    .description("MEOGO api명세 입니다.")
                    .version("v0.0.1")
            )
    }
}
