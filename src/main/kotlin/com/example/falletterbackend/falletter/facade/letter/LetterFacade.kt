package com.example.falletterbackend.falletter.facade.letter

import com.example.falletterbackend.falletter.entity.letter.Letter
import com.example.falletterbackend.falletter.entity.letter.repository.LetterRepository
import com.example.falletterbackend.falletter.entity.user.User
import com.example.falletterbackend.falletter.exception.letter.LetterNoAccessPermission
import com.example.falletterbackend.falletter.exception.letter.LetterNotFoundException
import com.example.falletterbackend.falletter.exception.letter.LetterNotReceivedException
import com.example.falletterbackend.falletter.exception.letter.LetterNotSendException
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class LetterFacade(
    private val letterRepository: LetterRepository
) {
    fun getSentLetterWithAccess(id: Long, user: User): Letter {
        return letterRepository.findByIdAndSender(id, user)
            ?: throw LetterNoAccessPermission
    }

    fun getReceivedLetterWithAccess(id: Long, userId: Long): Letter {
        return letterRepository.findByIdAndReception_Id(id, userId)
            ?: throw LetterNoAccessPermission
    }

    fun getUnpassedLetter(id: Long): Letter {
        return letterRepository.findByIdAndIsPassed(id, false)
            ?: throw LetterNotFoundException
    }

    fun getUnpassedLetters(): List<Letter> {
        val letters = letterRepository.findAllByIsPassed(false)
        if (letters.isEmpty()) {
            throw LetterNotFoundException
        }
        return letters
    }

    fun getUnpassedLettersWithRelations(): List<Letter> {
        val letters = letterRepository.findAllUnpassedWithRelations()
        if (letters.isEmpty()) {
            throw LetterNotFoundException
        }
        return letters
    }

    fun getReceivedLetters(userId: Long): List<Letter> {
        val letters = letterRepository.findAllByReception_Id(userId)
        if (letters.isEmpty()) {
            throw LetterNotReceivedException
        }
        return letters
    }

    fun getReceivedPassedLettersWithSender(userId: Long): List<Letter> {
        val letters = letterRepository.findAllByReceptionIdAndPassedWithSender(userId)
        if (letters.isEmpty()) {
            throw LetterNotReceivedException
        }
        return letters
    }

    fun getSentLetters(user: User): List<Letter> {
        val letters = letterRepository.findAllBySender(user)
        if (letters.isEmpty()) {
            throw LetterNotSendException
        }
        return letters
    }

    fun getSentPassedLettersWithReception(user: User): List<Letter> {
        val letters = letterRepository.findAllBySenderAndPassedWithReception(user)
        if (letters.isEmpty()) {
            throw LetterNotSendException
        }
        return letters
    }

    fun saveLetter(letter: Letter): Letter {
        return letterRepository.save(letter)
    }

    fun saveAllLetters(letters: List<Letter>): List<Letter> {
        return letterRepository.saveAll(letters)
    }

    fun getUndeliveredLettersBefore(threshold: LocalDateTime): List<Letter> {
        return letterRepository.findAllUndeliveredBefore(threshold)
    }
}
