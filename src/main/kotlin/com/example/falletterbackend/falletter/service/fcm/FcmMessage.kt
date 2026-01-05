package com.example.falletterbackend.falletter.service.fcm

object FcmMessage {
    object Warning {
        const val TITLE = "경고 알림"
        const val BODY = "계정에 경고가 부여되었습니다. 자세한 내용은 앱에서 확인해주세요."
    }

    object Block {
        const val TITLE = "정지 알림"
        fun body(days: Int) = "계정이 ${days}일간 정지되었습니다. 자세한 내용은 앱에서 확인해주세요."
    }

    object Comment {
        const val TITLE = "새 댓글"
        const val BODY = "회원님의 게시글에 새 댓글이 달렸습니다."
    }

    object BrickActivation {
        const val TITLE = "브릭 활성화"
        const val BODY = "브릭 타이머가 활성화되었습니다."
    }

    object Brick {
        const val TITLE = "브릭 알림"
        const val BODY = "새로운 브릭이 도착했습니다."
    }

    object Letter {
        const val TITLE = "새 래터 도착"
        const val BODY = "새로운 래터가 도착했습니다."
    }

    object LetterSent {
        const val TITLE = "래터 전송 완료"
        const val BODY = "래터가 성공적으로 전송되었습니다."
    }

    object AdminNotice {
        const val TITLE = "공지사항"
        fun body(noticeTitle: String) = "새로운 공지사항: $noticeTitle"
    }
}
