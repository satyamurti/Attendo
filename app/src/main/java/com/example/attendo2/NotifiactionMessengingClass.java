package com.example.attendo2;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class NotifiactionMessengingClass extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        showNotifications(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
    }

    public void showNotifications(String title, String message){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this ,"MyNotifications")
                .setContentTitle(title)
                .setSmallIcon(R.drawable.icon)
                .setAutoCancel(true)
                .setContentText(message);

        NotificationManagerCompat compat = NotificationManagerCompat.from(this);
        compat.notify(999,builder.build());
    }
 }
