package com.xtl.mapper;

import com.xtl.pojo.Nurse;
import com.xtl.pojo.NurseExt;

/**
 * 护士实体类接口
 * @author 31925
 */
public interface  NurseMapper {
    /**
     * 根据id查询护士信息
     * @param id 护士的id
     * @return  查询到的护士
     */
    NurseExt queryNurseExtById(Integer id);

    /**
     * 根据id查询护士信息
     * @param id 护士id
     * @return 护士结果
     */
    Nurse queryNurseById(Integer id);

    /**
     * 根据id查询护士信息
     * @param id 护士id
     * @return 护士结果
     */
    Nurse queryNurseById2(Integer id);
}
