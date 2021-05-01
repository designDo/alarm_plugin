package com.sia.alarm_plugin;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.AlarmClock;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.Map;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/**
 * AlarmPlugin
 */
public class AlarmPlugin implements FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;

  private Context context;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    context = flutterPluginBinding.getApplicationContext();
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "alarm_plugin");
    channel.setMethodCallHandler(this);
  }

  @RequiresApi(api = Build.VERSION_CODES.KITKAT) @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    } else if (call.method.equals("add2Alarm")) {
      add2Alarm(AlarmEvent.parse((Map<String, Object>) call.arguments));
      result.success("success");
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }


  @RequiresApi(api = Build.VERSION_CODES.KITKAT) private void add2Alarm(AlarmEvent event) {
    Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
        //闹钟的小时
        .putExtra(AlarmClock.EXTRA_HOUR, event.hour)
        //闹钟的分钟
        .putExtra(AlarmClock.EXTRA_MINUTES, event.minutes)
        //响铃时提示的信息
        .putExtra(AlarmClock.EXTRA_MESSAGE, event.title)
        //用于指定该闹铃触发时是否振动
        .putExtra(AlarmClock.EXTRA_VIBRATE, true)
        //一个 content: URI，用于指定闹铃使用的铃声，也可指定 VALUE_RINGTONE_SILENT 以不使用铃声。
        //如需使用默认铃声，则无需指定此 extra。
        //  .putExtra(AlarmClock.EXTRA_RINGTONE, ringtoneUri)
        //一个 ArrayList，其中包括应重复触发该闹铃的每个周日。
        // 每一天都必须使用 Calendar 类中的某个整型值（如 MONDAY）进行声明。
        //对于一次性闹铃，无需指定此 extra
        .putExtra(AlarmClock.EXTRA_DAYS, event.days)
        //如果为true，则调用startActivity()不会进入手机的闹钟设置界面
        .putExtra(AlarmClock.EXTRA_SKIP_UI, true);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(intent);
  }
}
