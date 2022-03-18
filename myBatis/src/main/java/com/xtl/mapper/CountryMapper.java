package com.xtl.mapper;

import com.xtl.pojo.Country;

/**
 * 国家实体类接口
 * @author 31925
 */
public interface CountryMapper {
    /**
     * 查询国家
     * @param id 国家id
     * @return  国家
     */
    Country queryCountryById(Integer id);

    /**
     * 查询国家
     * @param id 国家id
     * @return  国家
     */
    Country queryCountryById2(Integer id);
}
