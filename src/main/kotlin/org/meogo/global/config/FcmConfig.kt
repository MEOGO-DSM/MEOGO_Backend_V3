package org.meogo.global.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import java.io.File
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths
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
                URL(url).openStream().use { inputStream ->
                    Files.copy(inputStream, Paths.get(PATH))
                    val tempfile = File(PATH)
                    val options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(tempfile.inputStream()))
                        .build()
                    FirebaseApp.initializeApp(options)

                    tempfile.delete()
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
