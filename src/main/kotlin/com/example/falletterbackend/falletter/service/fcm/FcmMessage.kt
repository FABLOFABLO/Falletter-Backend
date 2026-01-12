package com.example.falletterbackend.falletter.service.fcm

object FcmMessage {
    object Warning {
        const val TITLE = "정책 위반 행위 경고 안내"
        const val BODY = "회원님 계정에 경고가 부여되었습니다. 자세한 내용은 앱에서 확인해주세요."
        const val IMAGE = "" // TODO: 이미지 URL 설정
    }

    object Block {
        const val TITLE = "계정 이용 일시 정지 안내"
        const val IMAGE = "" // TODO: 이미지 URL 설정
        fun body(days: Int) = "회원님 계정이 ${days}일간 정지되었습니다. 자세한 내용은 앱에서 확인해주세요."
    }

    object Comment {
        const val TITLE = "익명 회원이 댓글이 달렸어요!"
        const val BODY = "회원님의 게시글에 새 댓글이 달렸습니다."
        const val IMAGE = "" // TODO: 이미지 URL 설정
    }

    object BrickActivation {
        const val TITLE = "브릭 타이머가 활성화가 되었어요!"
        const val BODY = "지금 바로 질문을 선택해보세요,"
        const val IMAGE = "" // TODO: 이미지 URL 설정
    }

    object Brick {
        const val TITLE = "브릭 알림"
        const val BODY = "새로운 브릭이 도착했습니다."
        const val IMAGE = "" // TODO: 이미지 URL 설정
    }

    object Letter {
        const val TITLE = "새 래터 도착"
        const val BODY = "새로운 래터가 도착했습니다."
        const val IMAGE = "" // TODO: 이미지 URL 설정
    }

    object LetterSent {
        const val TITLE = "래터 전송 완료"
        const val BODY = "래터가 성공적으로 전송되었습니다."
        const val IMAGE = "" // TODO: 이미지 URL 설정
    }

    object AdminNotice {
        const val TITLE = "공지사항"
        const val IMAGE = "" // TODO: 이미지 URL 설정
    }
}
