class DeviceInfo {
  ///设备号.
  final String serialNumber;

  ///型号.
  final String model;

  ///厂商
  final String brand;

  ///SDK
  final int androidSdkInt;

  const DeviceInfo(this.serialNumber, this.model,this.brand,this.androidSdkInt)
      : assert(serialNumber != null),
        assert(model != null),
        assert(androidSdkInt != null),
        assert(brand != null);
}