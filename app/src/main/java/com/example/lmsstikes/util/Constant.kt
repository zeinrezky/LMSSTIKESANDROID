package com.example.lmsstikes.util

object Constant {


    const val BASE_URL = "http://api.stikes-apps.space/"
    const val PDF_URL = "https://docs.google.com/viewer?embedded=true&url="

    object Role {
        const val DOSEN = "DOSEN"
        const val MAHASISWA = "MAHASISWA"
    }

    object Thread {
        const val REPLY = "REPLY THREAD"
        const val NEW = "NEW THREAD"
        const val OPEN = "OPEN"
        const val LOCKED = "LOCKED"
    }

    object Header {
        const val TOKEN = "token"
        const val CACHE = "Cache-Control"
        const val URL = "URL"

    }

    object StatusCode {
        const val CODE_INVALID_TOKEN = -1
        const val CODE_INVALID_REQUEST = 0
        const val CODE_SUCCESS = 1
        const val CODE_NO_DATA = 2
        const val CODE_INACTIVE_USER = 3
        const val CODE_VERIFY_ACCOUNT = 4
        const val CODE_EMAIL_VERIFICATION = 5
        const val CODE_FORCE_UPDATE = 6
        const val CODE_SIMPLE_APP_UPDATE_ALERT = 7
        const val CODE_UNDER_MAINTENANCE = 8
        const val CODE_SOCIAL_ID_UNAUTHORIZED = 11
    }
}