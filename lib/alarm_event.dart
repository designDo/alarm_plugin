class AlarmEvent {
  String title;
  String description;
  int hour;
  int minutes;
  List<int> days;

  Map<String, dynamic> toJson() {
    Map<String, dynamic> map = {};
    map['title'] = title;
    map['description'] = description;
    map['hour'] = hour;
    map['minutes'] = minutes;
    map['days'] = days;
    return map;
  }
}
