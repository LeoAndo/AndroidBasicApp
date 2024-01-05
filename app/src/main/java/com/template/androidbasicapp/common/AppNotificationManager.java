package com.template.androidbasicapp.common;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.template.androidbasicapp.AppLogger;
import com.template.androidbasicapp.R;
import com.template.androidbasicapp.ui.activity.NotificationInfoActivity;

import java.util.UUID;

/**
 * 通知処理
 */
public final class AppNotificationManager {
    // 起動先コンポーネント(Activity, Serviceなど)にデータを渡すためのIntent Extra Key - START
    public static final String EXTRA_KEY_NOTIFICATION_ID = "notificationId";
    public static final String EXTRA_KEY_TITLE = "title";
    // END
    /**
     * 異常系の確認用定数
     */
    public static final int DEFAULT_NOTIFICATION_ID = 1;
    /**
     * 異常系の確認用定数
     */
    public static final int DEFAULT_PENDING_INTENT_REQUEST_CODE = 1;
    @NonNull
    private final Context context;
    @NonNull
    private final NotificationManager notificationManager;

    public enum NotificationPriority {
        MIN(NotificationCompat.PRIORITY_MIN),
        LOW(NotificationCompat.PRIORITY_LOW),
        DEFAULT(NotificationCompat.PRIORITY_DEFAULT),
        HIGH(NotificationCompat.PRIORITY_HIGH);

        private final int priority;

        NotificationPriority(final int priority) {
            this.priority = priority;
        }

        public int getPriority() {
            return priority;
        }
    }

    // https://developer.android.com/training/notify-user/channels?hl=ja#importance
    public enum NotificationChannelId {
        MIN_PRIORITY("重要度 低の通知チャネル"),
        LOW_PRIORITY("重要度 中の通知チャネル"),
        DEFAULT_PRIORITY("重要度 高の通知チャネル"),
        HIGH_PRIORITY("重要度 緊急の通知チャネル");
        @NonNull
        private final String userVisibleName;

        NotificationChannelId(@NonNull final String userVisibleName) {
            this.userVisibleName = userVisibleName;
        }

        @NonNull
        public String getUserVisibleName() {
            return userVisibleName;
        }
    }

    /**
     * 初期化を行う.
     *
     * @param context コンテキスト
     */
    public AppNotificationManager(@NonNull final Context context) {
        this.context = context;
        notificationManager = context.getSystemService(NotificationManager.class);
    }

    /**
     * アプリで使用するNotificationChannelを一括登録する.
     */
    public void createNotificationChannels() {
        for (NotificationChannelId notificationChannelId : NotificationChannelId.values()) {
            // TODO: 2024/01/04 @Importanceアノテーションの影響で、
            //  enumでNotificationManager.IMPORTANCE_XXXの定数を持つとワーニングが出るので、ここでswitch判定する
            final int importance;
            switch (notificationChannelId) {
                case MIN_PRIORITY:
                    importance = NotificationManager.IMPORTANCE_MIN;
                    break;
                case LOW_PRIORITY:
                    importance = NotificationManager.IMPORTANCE_LOW;
                    break;
                case DEFAULT_PRIORITY:
                    importance = NotificationManager.IMPORTANCE_DEFAULT;
                    break;
                case HIGH_PRIORITY:
                    importance = NotificationManager.IMPORTANCE_HIGH;
                    break;
                default:
                    throw new IllegalArgumentException("unknown enum value: " + notificationChannelId);
            }
            final NotificationChannel notificationChannel = new NotificationChannel(
                    notificationChannelId.name(),
                    notificationChannelId.getUserVisibleName(),
                    importance);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    /**
     * idを指定して通知を削除する.
     *
     * @param notificationId Notificationに指定する一意のid
     */
    public void cancelNotification(final int notificationId) {
        AppLogger.d("notificationId: " + notificationId);
        notificationManager.cancel(notificationId);
    }

    /**
     * 通知を表示する.
     *
     * @param notificationId Notificationに指定する一意のid
     * @param requestCode    通知コンテンツ用のPendingIntentに指定するrequestCode　一意のid
     * @param channelId      NotificationChannelに指定する一意のid
     * @param title          通知コンテンツに設定するタイトル
     * @param text           通知コンテンツに設定するテキスト
     * @param priority       通知のpriority
     */
    public void showNotification(final int notificationId,
                                 final int requestCode,
                                 @NonNull final String channelId,
                                 @NonNull final String title,
                                 @NonNull final String text,
                                 final int priority) {
        final Intent intent = new Intent(context, NotificationInfoActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK)
                .putExtra(EXTRA_KEY_NOTIFICATION_ID, notificationId)
                .putExtra(EXTRA_KEY_TITLE, title);
        final PendingIntent contentIntent =
                PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_IMMUTABLE);
        final Notification notification = createNotificationBuilder(notificationId, contentIntent, channelId, title, text, priority).build();
        notificationManager.notify(notificationId, notification);
    }

    /**
     * 通知を表示する.
     *
     * @param title 通知コンテンツに設定するタイトル
     * @param text  通知コンテンツに設定するテキスト
     */
    public void showFooBarNotification(@NonNull final String title, @NonNull final String text) {
        final int notificationId = UUID.randomUUID().hashCode();
        final int requestCode = UUID.randomUUID().hashCode();
        final String channelId = NotificationChannelId.DEFAULT_PRIORITY.name();
        final int priority = NotificationPriority.DEFAULT.getPriority();

        final Intent intent = new Intent(context, NotificationInfoActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK)
                .putExtra(EXTRA_KEY_NOTIFICATION_ID, notificationId)
                .putExtra(EXTRA_KEY_TITLE, title);
        final PendingIntent contentIntent =
                PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_IMMUTABLE);
        final Notification notification =
                createNotificationBuilder(
                        notificationId, contentIntent, channelId, title, text, priority
                ).build();
        notificationManager.notify(notificationId, notification);
    }

    /**
     * <pre>
     *     NotificationCompat.Builderを作成する.ここでアプリ共通の設定を行う.
     *     Google純正のアラームアプリ(DeskClock)のKotlinコードを参考にしている.
     * </pre>
     *
     * @param notificationId Notificationに指定する一意のid
     * @param contentIntent  通知コンテンツ用のPendingIntent
     * @param channelId      NotificationChannelに指定する一意のid
     * @param title          通知コンテンツに設定するタイトル
     * @param text           通知コンテンツに設定するテキスト
     * @param priority       通知のpriority
     * @return {@link NotificationCompat.Builder} インスタンス
     * @see <a href="https://cs.android.com/android/platform/superproject/main/+/main:packages/apps/DeskClock/src/com/android/deskclock/alarms/AlarmNotifications.kt;l=175?hl=ja">...</a>
     */
    @NonNull
    private NotificationCompat.Builder createNotificationBuilder(
            final int notificationId,
            @NonNull final PendingIntent contentIntent,
            @NonNull final String channelId,
            @NonNull final String title,
            @NonNull final String text,
            final int priority) {
        AppLogger.d("contentIntent hashCode: " + contentIntent.hashCode());
        AppLogger.d("notificationId: " + notificationId + " requestCode: " + channelId +
                " title: " + title + " text: " + text + " priority: " + priority);
        final NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, channelId)
                        // .setShowWhen(false) // targetSDK 24以降をターゲットとするアプリはデフォルトfalse
                        .setContentTitle(title)
                        .setContentText(text)
                        //.setColor(context.getColor(R.color.default_background))
                        .setSmallIcon(R.drawable.ic_splash)
                        .setAutoCancel(false)
                        .setPriority(priority)// TODO: setPriorityの設定はOS8以上のデバイスでは無視され、NotificationChannelのimportanceの設定値が適用される.本アプリはOS10以上をターゲットにしているため削除できる.
                        .setCategory(NotificationCompat.CATEGORY_EVENT)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                        //.setSortKey()
                        //.setGroup()
                        .setLocalOnly(true);
        builder.setContentIntent(contentIntent);
        return builder;
    }
}
