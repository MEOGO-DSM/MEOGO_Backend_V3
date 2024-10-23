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

    fun sendMessages(fcmTokens: List<String>, title: String, message: String) {
        val multicastMessage = createMulticastMessage(fcmTokens, title, message)
        sendMulticast(multicastMessage)
    }

    fun sendMessage(fcmToken: String, title: String, message: String) {
        val multicastMessage = createMulticastMessage(listOf(fcmToken), title, message)
        sendMulticast(multicastMessage)
    }

    private fun createMulticastMessage(fcmTokens: List<String>, title: String, message: String) =
        MulticastMessage.builder()
            .addAllTokens(fcmTokens)
            .setNotification(buildNotification(title, message))
            .setAndroidConfig(buildAndroidConfig(title, message))
            .setApnsConfig(buildApnsConfig(title, message))
            .build()

    private fun buildNotification(title: String, message: String) =
        Notification.builder()
            .setTitle(title)
            .setBody(message)
            .build()

    private fun buildAndroidConfig(title: String, message: String) =
        AndroidConfig.builder()
            .putData("title", title)
            .putData("body", message)
            .build()!!

    private fun buildApnsConfig(title: String, message: String) =
        ApnsConfig.builder()
            .setAps(
                Aps.builder()
                    .putCustomData("title", title)
                    .putCustomData("body", message)
                    .build()
            ).build()

    private fun sendMulticast(multicastMessage: MulticastMessage) {
        try {
            firebase.sendMulticastAsync(multicastMessage)
        } catch (e: FirebaseMessagingException) {
            throw FcmException
        }
    }
}
