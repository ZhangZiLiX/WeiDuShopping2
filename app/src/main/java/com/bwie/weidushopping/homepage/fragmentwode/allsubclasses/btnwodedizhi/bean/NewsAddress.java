package com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodedizhi.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * date:2018/12/14
 * author:张自力(DELL)
 * function:  我的地址存储封装类
 *
 *   使用GreenDao存储  db为公共的
 *
 */

@Entity
public class NewsAddress {

    @Id(autoincrement = true)
    private long id;
    private String userName;//收件人
    private String userPhone;//手机号
    private String userDiQu;//所在地区
    private String userXiangXiAddress;//详细地址
    private String userBianMa;//邮政编码
    @Generated(hash = 1673980291)
    public NewsAddress(long id, String userName, String userPhone, String userDiQu,
            String userXiangXiAddress, String userBianMa) {
        this.id = id;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userDiQu = userDiQu;
        this.userXiangXiAddress = userXiangXiAddress;
        this.userBianMa = userBianMa;
    }
    @Generated(hash = 859594166)
    public NewsAddress() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserPhone() {
        return this.userPhone;
    }
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
    public String getUserDiQu() {
        return this.userDiQu;
    }
    public void setUserDiQu(String userDiQu) {
        this.userDiQu = userDiQu;
    }
    public String getUserXiangXiAddress() {
        return this.userXiangXiAddress;
    }
    public void setUserXiangXiAddress(String userXiangXiAddress) {
        this.userXiangXiAddress = userXiangXiAddress;
    }
    public String getUserBianMa() {
        return this.userBianMa;
    }
    public void setUserBianMa(String userBianMa) {
        this.userBianMa = userBianMa;
    }

}
