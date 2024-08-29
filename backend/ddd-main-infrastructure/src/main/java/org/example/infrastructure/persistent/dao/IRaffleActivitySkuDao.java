package org.example.infrastructure.persistent.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.infrastructure.persistent.po.RaffleActivitySku;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 商品sku dao
 * @create 2024-03-16 11:04
 */
@Mapper
public interface IRaffleActivitySkuDao {

    RaffleActivitySku queryActivitySku(Long sku);

}
