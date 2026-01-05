package com.example.falletterbackend.common.config.fcm

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.File

@Configuration
class FcmConfig {

    @Bean
    fun firebaseMessaging(): FirebaseMessaging {
        if (FirebaseApp.getApps().isEmpty()) {
            val options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(File(PATH).inputStream()))
                .build()
            FirebaseApp.initializeApp(options)
        }
        return FirebaseMessaging.getInstance()
    }

    companion object {
        private const val PATH = "./serviceAccountKey.json"
    }
}
