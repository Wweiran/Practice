package cn.edu.scut.wweiran.fragmentpractice;

import java.io.Serializable;

/**
 * Created by WANGWEIRAN on 2017/3/31.
 * 为了测试序列化保存数据
 */

public class User  implements Serializable{

    private String name;
    private String password;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
