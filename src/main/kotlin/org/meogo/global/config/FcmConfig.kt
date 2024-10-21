package org.meogo.global.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import java.net.URL
import javax.annotation.PostConstruct

@Configuration
class FcmConfig(
    @Value("\${firebase.url}")
    private val url: String
) {
    @PostConstruct
    fun initializeFirebaseApp() {
        try {
            if (FirebaseApp.getApps().isEmpty()) {
                // URL에서 InputStream을 열고 FirebaseOptions를 초기화
                URL(url).openStream().use { inputStream ->
                    val options = FirebaseOptions.builder()
                        // InputStream을 통해 인증 정보 설정
                        .setCredentials(GoogleCredentials.fromStream(inputStream))
                        .build()
                    FirebaseApp.initializeApp(options)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        private const val PATH = "./firebase_credentials.json"
    }
}
