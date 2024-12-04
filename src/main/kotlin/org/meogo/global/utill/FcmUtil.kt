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

    fun sendMessage(fcmToken: List<String>, title: String, content: String) {
        val message = messageSetting(fcmToken, title, content)

        try {
            firebase.sendMulticastAsync(message)
        } catch (e: FirebaseMessagingException) {
            throw FcmException
        }
    }

    fun messageSetting(fcmToken: List<String>, title: String, content: String) =
        MulticastMessage.builder()
            .addAllTokens(fcmToken)
            .setNotification(
                Notification.builder()
                    .setTitle(title)
                    .setBody(content)
                    .build()
            )
            .setAndroidConfig(
                AndroidConfig.builder()
                    .putData("title", title)
                    .putData("body", content)
                    .build()
            )
            .setApnsConfig(
                ApnsConfig.builder()
                    .setAps(
                        Aps.builder()
                            .putCustomData("title", title)
                            .putCustomData("body", content)
                            .build()
                    ).build()
            ).build()!!
}
