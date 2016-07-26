package com.xdx.dllo.beautygoodsdemo.into;

import com.xdx.dllo.beautygoodsdemo.liteorm.LiteOrmCollectBean;
import com.xdx.dllo.beautygoodsdemo.liteorm.LiteOrmMyBean;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by dllo on 16/7/23.
 */
public class BombBean extends BmobObject {
    private String userName;
    private List<LiteOrmCollectBean> data;
    private LiteOrmMyBean liteOrmMyBean;

    public List<LiteOrmCollectBean> getData() {
        return data;
    }

    public void setData(List<LiteOrmCollectBean> data) {
        this.data = data;
    }

    public LiteOrmMyBean getLiteOrmMyBean() {
        return liteOrmMyBean;
    }

    public void setLiteOrmMyBean(LiteOrmMyBean liteOrmMyBean) {
        this.liteOrmMyBean = liteOrmMyBean;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
