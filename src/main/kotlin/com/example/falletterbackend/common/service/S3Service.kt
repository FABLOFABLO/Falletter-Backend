package com.example.falletterbackend.common.service

import com.example.falletterbackend.falletter.exception.s3.S3DeleteFailedException
import com.example.falletterbackend.falletter.exception.s3.S3InvalidUrlException
import com.example.falletterbackend.falletter.exception.s3.S3UploadFailedException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.exception.SdkException
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest
import software.amazon.awssdk.services.s3.model.GetUrlRequest
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.util.UUID

@Service
class S3Service(
    private val s3Client: S3Client,

    @Value("\${cloud.aws.s3.bucket}")
    private val bucket: String
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun upload(file: MultipartFile, directory: String): String {
        val fileName = createFileName(file.originalFilename ?: "file")
        val key = "$directory/$fileName"

        val putObjectRequest = PutObjectRequest.builder()
            .bucket(bucket)
            .key(key)
            .contentType(file.contentType)
            .build()

        try {
            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.inputStream, file.size))
        } catch (e: SdkException) {
            log.error("Failed to upload file to S3: key=$key", e)
            throw S3UploadFailedException
        }

        return getFileUrl(key)
    }

    fun delete(fileUrl: String) {
        val key = extractKeyFromUrl(fileUrl)

        val deleteObjectRequest = DeleteObjectRequest.builder()
            .bucket(bucket)
            .key(key)
            .build()

        try {
            s3Client.deleteObject(deleteObjectRequest)
        } catch (e: SdkException) {
            log.error("Failed to delete file from S3: key=$key", e)
            throw S3DeleteFailedException
        }
    }

    fun getFileUrl(key: String): String {
        val getUrlRequest = GetUrlRequest.builder()
            .bucket(bucket)
            .key(key)
            .build()

        return s3Client.utilities().getUrl(getUrlRequest).toString()
    }

    private fun createFileName(originalFileName: String): String {
        val extension = originalFileName.substringAfterLast(".", "")
        val uuid = UUID.randomUUID().toString()
        return if (extension.isNotEmpty()) "$uuid.$extension" else uuid
    }

    private fun extractKeyFromUrl(fileUrl: String): String {
        val key = fileUrl.substringAfter("$bucket/")
        if (key.isEmpty() || key == fileUrl) {
            log.warn("Invalid S3 URL format: $fileUrl")
            throw S3InvalidUrlException
        }
        return key
    }
}
