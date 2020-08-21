package com.example.lmsstikes.util

object Constant {


    const val BASE_URL = "http://cms.stikesrshusada.ac.id/api/"
    enum class Type(val value: String) {
        S("S"), G("G"), F("F")
    }

    const val CallCenter = "021 5011 2100"
    const val CustomerService = "0812 1803 4568"

    const val MaxImageSize = 2000000 // 2 MB

    const val DefaultLatitude = -6.175392
    const val DefaultLongitude = 106.827153
    const val CenterLatitude = -6.247491
    const val CenterLongitude = 106.770192
    const val CenterRadius = 45200 // 45.2 KM

    const val SearchWestLatitude = -6.616359
    const val SearchWestLongitude = 106.155626
    const val SearchEastLatitude = -5.905418
    const val SearchEastLongitude = 107.412112

    const val MapZoomMin = 11f
    const val MapZoomAddress = 16f
    const val MapZoomTracking = 14f

    const val OpenProject = "OpenProject"

    const val ServiceID = "ServiceID"
    const val ServiceName = "ServiceName"

    const val Tukang = "Tukang"

    const val Source = "Source"
    const val Edit = "Edit"

    const val CancelTukang = "CancelTukang"
    const val CancelOrder = "CancelOrder"

    const val WebViewUrl = "WebViewUrl"
    const val KanggoIdUrl = "https://www.kanggo.id/"
    const val PrivacyPolicyUrl = "https://www.kanggo.id/privacy-policy/"
    const val TermConditionUrl = "https://www.kanggo.id/terms-and-condition/"

    const val SKUrl = "https://www.kanggo.id/syarat-dan-ketentuan/"
    const val KPUrl = "https://www.kanggo.id/kebijakan-privasi/"

    const val KanggoCustomerPlayStoreUrl = "market://details?id=id.kanggo.kanggo"
    const val KanggoWorkerPlayStoreUrl = "market://details?id=com.kanggo.worker"

    const val AddressID = "AddressID"
    const val Address = "Address"
    const val AddressDetail = "AddressDetail"
    const val BuildingType = "BuildingType"
    const val Latitude = "Latitude"
    const val Longitude = "Longitude"

    const val RekeningID = "RekeningID"
    const val RekeningBank = "RekeningBank"
    const val RekeningNumber = "RekeningNumber"
    const val RekeningName = "RekeningName"

    const val PaymentMethodCreditCard = "CREDIT_CARD"
    const val PaymentMethodTransferBank = "BANK_TRANSFER"

    const val PaymentCreditCard = "Kartu Kredit"
    const val PaymentTransferBank = "Transfer Bank"

    const val PaymentID = "PaymentID"
    const val ProjectID = "ProjectID"

    const val BankCode = "BankCode"
    const val Amount = "Amount"
    const val VirtualAccount = "VirtualAccount"

    const val BCA = "BCA"
    const val BRI = "BRI"
    const val BNI = "BNI"
    const val MANDIRI = "MANDIRI"
    const val PERMATA = "PERMATA"

    const val UNPAID = "0"
    const val PAID = "1"
    const val EXPIRED = "2"

    const val BelumBayar = "belum dibayar"
    const val InPlanning = "in planning"
    const val MencariMitra = "mencari mitra"
    const val Active = "active"
    const val Completed = "completed"
    const val Cancelled = "cancelled"
    const val PembayaranExpired = "pembayaran sudah expired"

    const val DalamPersiapan = "Dalam Persiapan"
    const val Aktif = "Aktif"
    const val GagalBayar = "Gagal Bayar"
    const val Dibatalkan = "Dibatalkan"
    const val Selesai = "Selesai"

    const val DetailProjectID = "DetailProjectID"

    const val MaxTimePagi = "05:00"
    const val MaxTimeSiang = "14:00"

    const val EstimatorServiceID = 19
    const val EstimatorSkillID = 20

    const val ConfirmWaiting = 0
    const val ConfirmAccept = 1
    const val ConfirmReject = 2

    const val RequestSavedPlace = 0
    const val RequestAddAddress = 1
    const val RequestEditAddress = 2
    const val RequestCamera = 10
    const val RequestGallery = 11
    const val RequestMap = 20

    const val CHANNEL_ID: String = "Kanggo Notifications"
    const val CHANNEL_NAME: String = "Kanggo Channel Name"
    const val CHANNEL_DESC: String = "Kanggo Channel Desc"

    object NotificationParam {
        const val MESSAGE = "message"
        const val TITLE = "title"
        const val BODY = "body"
        const val TAG = "tag"
        const val DATA = "data"
        const val SENDER_ID = "sender_id"
        const val SENDER_IMAGE = "sender_image"
        const val SENDER_NAME = "sender_name"
        const val SENDER_USER_ID = "sender_user_id"
        const val SENDER_ROLE = "sender_role"
        const val SENDER_PHONE = "sender_phone"
        const val PROJECT_ID = "project_id"
        const val PHASE_ID = "phase_id"
        const val PARAMS = "params"
    }

    object NotificationTag {
        const val fcm_message = "fcm_message"
        const val project_ends_today = "project_ends_today"
        const val project_ends_soon = "project_ends_soon"
        const val project_date_change = "project_date_change"
        const val cc_assign_project = "cc_assign_project"
        const val cc_start_project = "cc_start_project"
        const val cc_start_project_tomorrow = "cc_start_project_tomorrow"
        const val worker_match = "worker_match"
        const val worker_complete_project = "worker_complete_project"
    }

    const val OPEN_CHAT_FROM: String = "OPEN_CHAT_FROM"
    const val OPEN_CHAT_FROM_PROJECT: String = "OPEN_CHAT_FROM_PROJECT"
    const val OPEN_CHAT_FROM_INBOX: String = "OPEN_CHAT_FROM_INBOX"
    const val OPEN_CHAT_FROM_NOTIF: String = "OPEN_CHAT_FROM_NOTIF"

    const val RECEIVER_ID: String = "RECEIVER_ID"
    const val RECEIVER_ROLE: String = "RECEIVER_ROLE"
    const val RECEIVER_NAME: String = "RECEIVER_NAME"
    const val RECEIVER_IMAGE: String = "RECEIVER_IMAGE"
    const val RECEIVER_PHONE: String = "RECEIVER_PHONE"

    object FireStore {
        var TAB_NAME_CHAT = "chat"
        var TAB_NAME_RECENT_CHAT = "recent_chat"
        var TAB_NAME_USER = "user"

        // Chat table
        const val FIELD_SENDER_ID = "sender_id"
        const val FIELD_SENDER_IMAGE = "sender_image"
        const val FIELD_SENDER_NAME = "sender_name"
        const val FIELD_SENDER_DEVICE_TOKEN = "sender_device_token"
        const val FIELD_SENDER_DEVICE_TYPE = "sender_device_type"
        const val FIELD_RECEIVER_ID = "receiver_id"
        const val FIELD_RECEIVER_IMAGE = "receiver_image"
        const val FIELD_RECEIVER_NAME = "receiver_name"
        const val FIELD_MESSAGE = "message"
        const val FIELD_MESSAGE_TYPE = "message_type"
        const val FIELD_CHAT_TIME = "chat_time"
        const val FIELD_CHAT_TIME_UTC = "chat_time_utc"
        const val FIELD_CHAT_READ = "chat_read"
        const val FIELD_RECENT_CHAT_ID = "recent_chat_id"

        // Recent Chat table
        const val FIELD_CHAT_COUNT = "chat_count"

        // User table
        const val FIELD_IS_ONLINE = "is_online"
    }

    object Role {
        const val COMPANY = "CC"
        const val PROJECT_MANAGER = "PM"
        const val WORKER = "W"
        const val CUSTOMER = "CUS"
    }

    object EmailPrefix {
        const val COMPANY = "cc_"
        const val PROJECT_MANAGER = "pm_"
        const val WORKER = "w_"
        const val CUSTOMER = "cus_"
    }

    const val FIREBASE_AUTH_PASSWORD = "12345678"

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