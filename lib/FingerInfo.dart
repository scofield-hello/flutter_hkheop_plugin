class FingerInfo {
  ///指纹特征.
  final String feature;

  ///指纹base64图像.
  final String bitmap;

  const FingerInfo(this.feature, this.bitmap)
      : assert(feature != null && feature.length > 0),
        assert(bitmap != null && bitmap.length > 0);
}