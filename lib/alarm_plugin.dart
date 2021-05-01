import 'dart:async';

import 'package:alarm_plugin/alarm_event.dart';
import 'package:flutter/services.dart';

class AlarmPlugin {
  static const MethodChannel _channel = const MethodChannel('alarm_plugin');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<String> add2Alarm(AlarmEvent event) async {
    final String message =
        await _channel.invokeMethod('add2Alarm', event.toJson());
    return message;
  }
}
