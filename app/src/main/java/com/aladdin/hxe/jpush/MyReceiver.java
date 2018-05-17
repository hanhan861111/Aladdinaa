package com.aladdin.hxe.jpush;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import com.aladdin.hxe.R;
import com.aladdin.hxe.activity.JpushTestActivity;
import com.aladdin.hxe.bean.MessageEventD;
import com.aladdin.hxe.bean.MessageEventE;
import com.aladdin.hxe.utils.SharedPreferencesUtils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * 自定义接收器
 * <p>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "JPush";
    public String extras;
    private String userId;
    private String isAuto;
    private Vibrator mVibrator;

    @Override
    public void onReceive(Context context, Intent intent) {
        userId = SharedPreferencesUtils.getString(context, "userId", "");
        isAuto = SharedPreferencesUtils.getString(context, "isAuto", "");
        Bundle bundle = intent.getExtras();
        Log.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
            //send the Registration Id to your server...

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
            processCustomMessage(context, bundle);

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知");
            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
            String string = bundle.getString(JPushInterface.EXTRA_ALERT);
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知的内容: " + string);
            //JPushInterface.setAlias(context,notifactionId,null);

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户点击打开了通知");

            //打开自定义的Activity
            Intent i = new Intent(context, JpushTestActivity.class);
            i.putExtras(bundle);
            //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(i);

        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            Log.w(TAG, "[MyReceiver]" + intent.getAction() + " connected state change to " + connected);
        } else {
            Log.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
        }
    }

    private String printBundle(Bundle bundle) {
        return bundle.toString();
    }


    /**
     * 实现自定义推送声音
     *
     * @param context
     * @param bundle
     */

    private void processCustomMessage(Context context, Bundle bundle) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(context);
//        notification.setAutoCancel(true)
//                .setContentText("您有新的黄小二订单")
//                .setContentTitle("新订单提醒")
//                .setSmallIcon(R.mipmap.ic_launcher);
        String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        if (!TextUtils.isEmpty(extras)) {
            try {
                JSONObject extraJson = new JSONObject(extras);
                if (null != extraJson && extraJson.length() > 0) {
                    String sound = extraJson.getString("shopid");
                    String status = extraJson.getString("status");
                    Log.d("mmmmmmmmmmmmmm",extraJson.toString());
                    if (userId.equals(sound) && (status.equals("10000")||status.equals("10005")) && isAuto.equals("")) {
                        EventBus.getDefault().post(new MessageEventD("NOTICE"));
                        MediaPlayer mMediaPlayer;
                        mMediaPlayer=MediaPlayer.create(context, R.raw.dd);
                        mMediaPlayer.setVolume(1.0f, 1.0f);
                        mMediaPlayer.start();
//                        notification.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.dd));
                    } else if (userId.equals(sound) && status.equals("10000")&&isAuto.equals("1")) {
                        //获取手机震动服务
                        mVibrator = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
                        mVibrator.vibrate(new long[]{1000, 1000}, -1);
                    }else if(userId.equals(sound) && status.equals("10012") && isAuto.equals("")){
                        String msgData = extraJson.getString("msgData");
                        Log.d("mmmmmm",msgData);
                        EventBus.getDefault().post(new MessageEventE(msgData));
                    }
                }
            } catch (JSONException e) {

            }

        }


        Intent mIntent = new Intent(context, JpushTestActivity.class);

        mIntent.putExtras(bundle);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mIntent, 0);

        notification.setContentIntent(pendingIntent);


       // notificationManager.notify(2, notification.build());

    }

}


