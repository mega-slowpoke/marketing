package org.example.domain.strategy.service.filter.beforeFilter;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.strategy.repository.IStrategyRepo;
import org.example.domain.strategy.service.executor.ILotteryExecutor;

import javax.annotation.Resource;

@Slf4j
public abstract class AbstractBeforeFilter implements IBeforeFilter {

    @Resource
    protected ILotteryExecutor iLotteryExecutor;

    @Resource
    protected IStrategyRepo iStrategyRepo;

    private AbstractBeforeFilter next = null;

    public AbstractBeforeFilter next() {
        return next;
    }

    public void appendNext(AbstractBeforeFilter nextFilterNode) {
        this.next = nextFilterNode;
    }


}
