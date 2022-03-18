package com.xtl.pojo;

/**
 * 护士扩展实体类
 * @author 31925
 */
public class NurseExt extends Nurse{
    private Integer no;
    private String name;
    private float money;

    @Override
    public String toString() {
        return "NurseExt{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }
}
