package com.sia.alarm_plugin;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by  on 2021/5/1.
 */
public class AlarmEvent {
  String title;
  String description;
  int hour;
  int minutes;
  ArrayList<Integer> days;

  public static AlarmEvent parse(Map<String, Object> json) {
    AlarmEvent event = new AlarmEvent();
    event.title = (String) json.get("title");
    event.description = (String) json.get("description");
    event.hour = (int) json.get("hour");
    event.minutes = (int) json.get("minutes");
    event.days = (ArrayList<Integer>) json.get("days");
    return event;
  }
}
