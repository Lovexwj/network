package com.example.httplibrary.bean;

import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.httplibrary.cacha.Database;
import com.example.httplibrary.gsonuiles.MyGson;
import com.example.httplibrary.uilts.MyLog;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class BeanUser {

    private String id;//主键
    private String gender;//0:男;1:女;2:其他&保密
    //    private Gender gender;//0:男;1:女;2:其他&保密
    @SerializedName(value = "name", alternate = {"userName"})
    private String name;//姓名昵称
    @SerializedName(value = "phone", alternate = {"loginName"})
    private String phone="";//电话登录账号
    @SerializedName(value = "avatar", alternate = {"headerImg"})
    private String avatar;//头像图片的链接地址
    @SerializedName(value = "birthday", alternate = {"birthDate"})
//    private Date birthday = new Date();//生日
    private Date birthday;//生日
    private String accessToken = "";//登录返回的 token
    private String constellation="";//星座
    private String country="";//国家
    private String idNo="";
    private String idType="";
    private String address="";
    private int regChannel;
    private Date authTime;
    private Date lastLogtime;
    private int totalTimes;
    private Date createTime;
    private Date updateTime;
    private int userStatus;
    private String logType="";
    private int userType;
    private String realName="";
    private int age;
    private String email="";
    private String mobile="";
    private Object flag1;
    private Object flag2;
    private Object flag3;
    private Object flag4;
    private Object flag5;
    private String trainNo="";
    private static boolean inTrain;
    private boolean isLogin;
    private long expireAt;// 1511239364747

    public long getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(long expireAt) {
        this.expireAt = expireAt;
    }
    public static boolean isInTrain() {
        return BeanUser.inTrain;
    }

    public static void setInTrain(boolean inTrain) {
        BeanUser.inTrain = inTrain;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;

    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
        MyLog.i(this,"setLogin:"+login);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        MyLog.i(this,"setAccessToken:"+accessToken);
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRegChannel() {
        return regChannel;
    }

    public void setRegChannel(int regChannel) {
        this.regChannel = regChannel;
    }

    public Date getAuthTime() {
        return authTime;
    }

    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
    }

    public Date getLastLogtime() {
        return lastLogtime;
    }

    public void setLastLogtime(Date lastLogtime) {
        this.lastLogtime = lastLogtime;
    }

    public int getTotalTimes() {
        return totalTimes;
    }

    public void setTotalTimes(int totalTimes) {
        this.totalTimes = totalTimes;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Object getFlag1() {
        return flag1;
    }

    public void setFlag1(Object flag1) {
        this.flag1 = flag1;
    }

    public Object getFlag2() {
        return flag2;
    }

    public void setFlag2(Object flag2) {
        this.flag2 = flag2;
    }

    public Object getFlag3() {
        return flag3;
    }

    public void setFlag3(Object flag3) {
        this.flag3 = flag3;
    }

    public Object getFlag4() {
        return flag4;
    }

    public void setFlag4(Object flag4) {
        this.flag4 = flag4;
    }

    public Object getFlag5() {
        return flag5;
    }

    public void setFlag5(Object flag5) {
        this.flag5 = flag5;
    }

    public static void save(Context con, String userJson) {
        Database.putString(con, BeanUser.class.getName(), userJson);
    }





    public static BeanUser get(Context con) {
        String userJson = Database.getString(con, BeanUser.class.getName());
        if (userJson != null && !"".equals(userJson)) {
            BeanUser user = MyGson.get().fromJson(userJson, BeanUser.class);
            if(user==null)
                return new BeanUser();
            return user;
        }
        return new BeanUser();
    }

    public static boolean isLogin(Context con) {
        return get(con).isLogin;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean isLogin(Fragment f) {
        return get(f.getContext()).isLogin;
    }

    public static void setTrainNo(Context con, String trainNo) {
        BeanUser user = get(con);
        user.setTrainNo(trainNo);
        save(con, MyGson.get().toJson(user));


    }
}
