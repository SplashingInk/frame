package com.xtl.pojo;

/**
 * @author 31925
 */
public class Card {
    /**
     * 会员卡编号
     * */
    private Integer no;
    private Float money;

    public Card() {
    }

    public Card(Integer no, Float money) {
        this.no = no;
        this.money = money;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Card{" +
                "no=" + no +
                ", money=" + money +
                '}';
    }
}
