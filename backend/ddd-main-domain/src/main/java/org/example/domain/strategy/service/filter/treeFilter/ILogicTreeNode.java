package org.example.domain.strategy.service.filter.treeFilter;


import org.example.domain.strategy.service.filter.treeFilter.factory.DefaultTreeFactory;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 规则树接口
 * @create 2024-01-27 11:14
 */
public interface ILogicTreeNode {

    DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId, String ruleValue);

}
