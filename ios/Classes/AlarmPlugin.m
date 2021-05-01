#import "AlarmPlugin.h"
#if __has_include(<alarm_plugin/alarm_plugin-Swift.h>)
#import <alarm_plugin/alarm_plugin-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "alarm_plugin-Swift.h"
#endif

@implementation AlarmPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftAlarmPlugin registerWithRegistrar:registrar];
}
@end
