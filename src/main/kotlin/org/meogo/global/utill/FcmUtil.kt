package org.meogo.global.utill

import com.google.firebase.messaging.AndroidConfig
import com.google.firebase.messaging.ApnsConfig
import com.google.firebase.messaging.Aps
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingException
import com.google.firebase.messaging.MulticastMessage
import com.google.firebase.messaging.Notification
import org.meogo.global.utill.exception.FcmException
import org.springframework.stereotype.Component

@Component
class FcmUtil {
    private val firebase: FirebaseMessaging
        get() = FirebaseMessaging.getInstance()

    fun sendMessage(fcmToken: List<String>, title: String, message: String) {
        val message = messageSetting(fcmToken, title, message)

        println("호우")
        try {
            firebase.sendMulticastAsync(message)
        } catch (e: FirebaseMessagingException) {
            throw FcmException
        }

        println("이게되네")
    }

    fun messageSetting(fcmToken: List<String>, title: String, message: String) =
        MulticastMessage.builder()
            .addAllTokens(fcmToken)
            .setNotification(
                Notification.builder()
                    .setTitle(title)
                    .setBody(message)
                    .build()
            )
            .setAndroidConfig(
                AndroidConfig.builder()
                    .putData("title", title)
                    .putData("body", message)
                    .build()
            )
            .setApnsConfig(
                ApnsConfig.builder()
                    .setAps(
                        Aps.builder()
                            .putCustomData("title", title)
                            .putCustomData("body", message)
                            .build()
                    ).build()
            ).build()
}
