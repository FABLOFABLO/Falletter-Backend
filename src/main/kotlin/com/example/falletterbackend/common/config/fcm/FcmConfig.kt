package com.example.falletterbackend.common.config.fcm

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource

@Configuration
class FcmConfig {

    @Bean
    fun firebaseApp(): FirebaseApp {
        if (FirebaseApp.getApps().isNotEmpty()) {
            return FirebaseApp.getInstance()
        }

        val resource = ClassPathResource("serviceAccountKey.json")
        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(resource.inputStream))
            .build()

        return FirebaseApp.initializeApp(options)
    }

    @Bean
    fun firebaseMessaging(firebaseApp: FirebaseApp): FirebaseMessaging {
        return FirebaseMessaging.getInstance(firebaseApp)
    }
}
