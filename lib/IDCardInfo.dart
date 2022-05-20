class IDCardInfo {
  ///卡号
  final String id;

  ///姓名
  final String name;

  ///性别
  final String sex;

  ///民族
  final String nation;

  ///出生日期
  final String birth;

  ///地址
  final String address;

  ///签发机关
  final String depart;

  ///有效期起始时间
  final String startDate;

  ///有效期截止时间
  final String endDate;

  ///身份证图片url
  final String url;
  IDCardInfo(
      {this.id,
        this.name,
        this.sex,
        this.nation,
        this.birth,
        this.address,
        this.depart,
        this.startDate,
        this.endDate,
        this.url});
}
