class FaceInfo {
  ///人脸建模值.
  final String feature;

  ///人脸图像路径.
  final String url;

  const FaceInfo(this.feature, this.url)
      : assert(feature != null && feature.length > 0),
        assert(url != null && url.length > 0);
}