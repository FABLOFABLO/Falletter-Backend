package com.example.falletterbackend.common.config.fcm

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.FileInputStream

@Configuration
class FcmConfig(

    @Value("\${firebase.credentials.path}")
    private val firebaseKeyPath: String

) {

    @Bean
    fun firebaseApp(): FirebaseApp {
        return FirebaseApp.getApps().firstOrNull()
            ?: run {
                val options = FirebaseOptions.builder()
                    .setCredentials(
                        GoogleCredentials.fromStream(FileInputStream(firebaseKeyPath))
                    )
                    .build()
                FirebaseApp.initializeApp(options)
            }
    }

    @Bean
    fun firebaseMessaging(firebaseApp: FirebaseApp): FirebaseMessaging {
        return FirebaseMessaging.getInstance(firebaseApp)
    }
}
