import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:HkHeop/HkHeop.dart';

void main() {
  const MethodChannel channel = MethodChannel('HkHeop');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await HkHeop.platformVersion, '42');
  });
}
