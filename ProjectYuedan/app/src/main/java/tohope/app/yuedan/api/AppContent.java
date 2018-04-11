package tohope.app.yuedan.api;

/**
 * Created by ChenZhihong on 2017/3/30.
 * 网络地址存放类
 */

public class AppContent {

    public static final String BASE_URL = "http://yue.ydongyun.cn/";//基本网址
    public static final String BETA = "API/";
    //token当天有效，后面所有接口都要用到这个参数
    public static final String GetToken = BASE_URL + BETA + "Token.ashx";
    //检查帐号是否存在
    public static final String CheckAccounts = BASE_URL + BETA + "Customer.ashx";
    //短信接口
    public static final String GetVerificationCode = BASE_URL + BETA + "MobileMessage.ashx";
    //用户注册帐号
    public static final String RegisterAccounts = BASE_URL + BETA + "Customer.ashx";
    //获取地区列表
    public static final String GetAreaList = BASE_URL + BETA + "Area.ashx";
    //获取城市广告列表
    public static final String GetAdvertListByCityID = BASE_URL + BETA + "Advert.ashx";
    //获取地区广告列表
    public static final String GetAdvertListByAreaID = BASE_URL + BETA + "Advert.ashx";
    //图片上传接口 post文件流
    public static final String SaveImage = BASE_URL + BETA + "SaveImage.ashx";
    //获取技能分类
    public static final String GetSkillTypeList = BASE_URL + BETA + "SkillType.ashx";
}
