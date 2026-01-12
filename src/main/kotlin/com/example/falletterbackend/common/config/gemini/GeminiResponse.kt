package com.example.falletterbackend.common.config.gemini

data class GeminiResponse(
    val candidates: List<Candidate>? = null
) {
    data class Candidate(
        val content: Content? = null
    )

    data class Content(
        val parts: List<Part>? = null
    )

    data class Part(
        val text: String? = null
    )

    fun extractText(): String? {
        return candidates
            ?.firstOrNull()
            ?.content
            ?.parts
            ?.firstOrNull()
            ?.text
            ?.trim()
            ?.split("\\s+".toRegex())
            ?.firstOrNull()
            ?.lowercase()
    }
}
