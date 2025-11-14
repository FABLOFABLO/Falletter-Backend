package com.example.falletterbackend.common.config.gemini

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class GeminiConfig(
    @Value("\${google.gemini.api.key}")
    private val geminiApiKey: String
) {

    private val restTemplate = RestTemplate()

    fun checkForProfanity(content: String): Boolean {
        val url =
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=$geminiApiKey"

        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
        }

        val requestBody = mapOf(
            "contents" to listOf(
                mapOf(
                    "parts" to listOf(
                        mapOf(
                            "text" to "다음 문장을 분석해서 판단해주세요.\n\n" +
                                    "조건:\n" +
                                    "1. 욕설, 비속어, 공격적 표현, 모욕, 인신공격, 과도한 비난, 조롱, 은근한 비하, 꼬집는 표현 등 **상대방에게 불쾌감을 줄 수 있는 뉘앙스가 포함되면** 반드시 'false' 한 단어만 출력.\n" +
                                    "2. 문장이 일반적이거나 부정적 감정은 있어도 욕설, 비난, 공격적 표현이 없다면 \"true\" 한 단어로만 반환.\n" +
                                    "3. 판단은 문맥과 뉘앙스를 기반으로 하며, 단어 하나하나만 보는 것이 아니라 전체 의미를 고려합니다.\n\n" +
                                    "분석할 문장: $content"
                        )
                    )
                )
            )
        )

        val entity = HttpEntity(requestBody, headers)

        val response = restTemplate.exchange(url, HttpMethod.POST, entity, Map::class.java)
        println("Gemini API Response: ${response.body}")

        val text = (response.body?.get("candidates") as? List<Map<String, Any>>)
            ?.get(0)
            ?.get("content")
            ?.let { contentMap ->
                val parts = (contentMap as Map<*, *>)["parts"] as? List<Map<String, Any>>
                parts?.get(0)?.get("text")?.toString()
            }
            ?.trim()
            ?.split("\\s+".toRegex())
            ?.get(0)
            ?.lowercase() ?: "false"

        return text == "true"
    }
}