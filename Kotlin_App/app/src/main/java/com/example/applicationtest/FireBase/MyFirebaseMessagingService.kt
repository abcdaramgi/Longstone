package com.example.applicationtest.FireBase

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.applicationtest.MainActivity
import com.example.applicationtest.R
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {
    companion object {
        const val TAG = "MessagingService"
        private const val CHANNEL_NAME = "Push Notification"
        private const val CHANNEL_DESCRIPTION = "Channel for Push Notification"
        private const val CHANNEL_ID = "Channel Id"
    }

    private val TAG = "FirebaseService"

    override fun onNewToken(token: String) {
        Log.d(TAG, "new Token: $token")

        // 토큰 값을 따로 저장
        val pref = this.getSharedPreferences("token", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("token", token).apply()
        editor.commit()
        Log.i(TAG, "성공적으로 토큰을 저장함")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "From: " + remoteMessage!!.from)

        // Notification 메시지를 수신할 경우
        // remoteMessage.notification?.body!! 여기에 내용이 저장되있음
        // Log.d(TAG, "Notification Message Body: " + remoteMessage.notification?.body!!)

        //받은 remoteMessage의 값 출력해보기. 데이터메세지 / 알림메세지
        Log.d(TAG, "Message data : ${remoteMessage.data}")
        Log.d(TAG, "Message noti : ${remoteMessage.notification}")

        if (remoteMessage.data.isNotEmpty()) {
            //알림생성
            sendNotification(remoteMessage)
//            Log.d(TAG, remoteMessage.data["title"].toString())
//            Log.d(TAG, remoteMessage.data["body"].toString())
        } else {
            Log.e(TAG, "data가 비어있습니다. 메시지를 수신하지 못했습니다.")
        }
    }

    /** 알림 생성 메서드 */
    private fun sendNotification(remoteMessage: RemoteMessage) {
        // RequestCode, Id를 고유값으로 지정하여 알림이 개별 표시
        val uniId: Int = (System.currentTimeMillis() / 7).toInt()

        // 일회용 PendingIntent : Intent 의 실행 권한을 외부의 어플리케이션에게 위임
        val intent = Intent(this, MainActivity::class.java)
        //각 key, value 추가
        for (key in remoteMessage.data.keys) {
            intent.putExtra(key, remoteMessage.data.getValue(key))
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) // Activity Stack 을 경로만 남김(A-B-C-D-B => A-B)
        val pendingIntent =
            PendingIntent.getActivity(this, uniId, intent, PendingIntent.FLAG_IMMUTABLE)

        // 알림 채널 이름
        val channelId = "my_channel"
        // 알림 소리
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        // 알림에 대한 UI 정보, 작업
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher) // 아이콘 설정
            .setContentTitle(remoteMessage.data["title"].toString()) // 제목
            .setContentText(remoteMessage.data["body"].toString()) // 메시지 내용
            .setAutoCancel(true) // 알람클릭시 삭제여부
            .setSound(soundUri)  // 알림 소리
            .setContentIntent(pendingIntent) // 알림 실행 시 Intent

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // 오레오 버전 이후에는 채널이 필요
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelId, "Notice", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        // 알림 생성
        notificationManager.notify(uniId, notificationBuilder.build())
    }

    /** Token 가져오기 */
    fun getFirebaseToken() {
        //비동기 방식
        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            Log.d(TAG, "token=${it}")
        }
    }


//    override fun onMessageReceived(remoteMessage: RemoteMessage) {
//        super.onMessageReceived(remoteMessage)
//        val notificationManager = NotificationManagerCompat.from(
//            applicationContext
//        )
//        var builder: NotificationCompat.Builder? = null
//        builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
//                val channel = NotificationChannel(
//                    CHANNEL_ID,
//                    CHANNEL_NAME,
//                    NotificationManager.IMPORTANCE_DEFAULT
//                )
//                notificationManager.createNotificationChannel(channel)
//            }
//            NotificationCompat.Builder(applicationContext, CHANNEL_ID)
//        } else {
//            NotificationCompat.Builder(applicationContext)
//        }
//        val title = remoteMessage.notification!!.title
//        val body = remoteMessage.notification!!.body
//        builder.setContentTitle(title)
//            .setContentText(body)
//            .setSmallIcon(R.drawable.ic_launcher_background)
//        val notification = builder.build()
//        notificationManager.notify(1, notification)
//    }

//    override fun onMessageReceived(remoteMessage: RemoteMessage) {
//        super.onMessageReceived(remoteMessage)
//        Log.d(TAG, "onMessageReceived() - remoteMessage : $remoteMessage")
//        Log.d(TAG, "onMessageReceived() - from : ${remoteMessage.from}")
//        Log.d(TAG, "onMessageReceived() - notification : ${remoteMessage.notification?.body}")
//
//        val type = remoteMessage.data["type"]?.let { NotificationType.valueOf(it) } ?: kotlin.run {
//            NotificationType.NORMAL  //type 이 null 이면 NORMAL type 으로 처리
//        }
//        val title = remoteMessage.data["title"]
//        val message = remoteMessage.data["message"]
//
//        Log.d(TAG, "onMessageReceived() - type : $type")
//        Log.d(TAG, "onMessageReceived() - title : $title")
//        Log.d(TAG, "onMessageReceived() - message : $message")
//
//        sendNotification(type, title, message)
//    }
//
//    private fun sendNotification(
//        type: NotificationType,
//        title: String?,
//        message: String?
//    ) {
//        val notificationManager =
//            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        //Oreo(26) 이상 버전에는 channel 필요
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(
//                CHANNEL_ID,
//                CHANNEL_NAME,
//                NotificationManager.IMPORTANCE_DEFAULT
//            )
//            channel.description = CHANNEL_DESCRIPTION
//            notificationManager.createNotificationChannel(channel)
//        }
//
//        //알림 생성
//        NotificationManagerCompat.from(this)
//            .notify((System.currentTimeMillis()/100).toInt(), createNotification(type, title, message))  //알림이 여러개 표시되도록 requestCode 를 추가
//    }

//    private fun createNotification(
//        type: NotificationType,
//        title: String?,
//        message: String?
//    ): Notification {
//
//        val intent = Intent(this, MainActivity::class.java).apply {
//            putExtra("notificationType", " ${type.title} 타입 ")
//            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
//        }
//        val pendingIntent = PendingIntent.getActivity(this, (System.currentTimeMillis()/100).toInt(), intent, FLAG_UPDATE_CURRENT)  //알림이 여러개 표시되도록 requestCode 를 추가
//
//        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
//            .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
//            .setContentTitle(title)
//            .setContentText(message)
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//            .setContentIntent(pendingIntent)  //알림 눌렀을 때 실행할 Intent 설정
//            .setAutoCancel(true)  //클릭 시 자동으로 삭제되도록 설정
//
//        when (type) {
//            NotificationType.NORMAL -> Unit
//            NotificationType.EXPANDABLE -> {
//                notificationBuilder.setStyle(
//                    NotificationCompat.BigTextStyle()
//                        .bigText("$message \n 😀 😃 😄 😁 😆 😅 😂 🤣 🥲 ☺️ 😊 😇 🙂 🙃 😉 😌 😍 🥰 😘 😗 😙 😚 😋 😛 😝 😜 🤪 🤨 🧐 🤓 😎 🥸 🤩 🥳 😏 😒 😞 😔 😟 😕 🙁 ☹️ 😣 😖 😫 😩 🥺 😢 😭 😤 😠 😡 🤬 🤯 😳 🥵 🥶 😱 😨 😰 😥 😓 🤗 🤔 🤭 🤫 🤥 😶 😐 😑 😬 🙄 😯 😦 😧 😮 😲 🥱 😴 🤤 😪 😵 🤐 🥴 🤢 🤮 🤧 😷 🤒 🤕")
//                )
//            }
//            NotificationType.CUSTOM -> {
//                notificationBuilder.setStyle(
//                    NotificationCompat.DecoratedCustomViewStyle()
//                )
//                    .setCustomContentView(
//                        RemoteViews(
//                            packageName,
//                            R.layout.view_custom_notification
//                        ).apply {
//                            setTextViewText(R.id.tv_custom_title, title)
//                            setTextViewText(R.id.tv_custom_message, message)
//                        }
//                    )
//            }
//        }
//        return notificationBuilder.build()
//    }
}