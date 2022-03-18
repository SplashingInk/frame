package com.xtl.mapper;

import com.xtl.pojo.Card;

/**
 * 会员卡接口
 * @author 31925
 */
public interface CardMapper {
    /**
     * 根据no查询会员卡
     * @param no 护士id
     * @return 会员卡
     */
    Card queryCardByNo(Integer no);
}
