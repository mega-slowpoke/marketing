#

### Lottery Process
Big picture: LotteryReq -> LotteryRes

Details: 
ILotteryService -> 唯一暴露的接口performLottery，接受LotteryReq，返回LotteryRes


实现ILotteryService.performLottery, 

defaultLotteryService extends abstractLotteryService implements ILotteryService

具体实现分为3部分
1. 先进行过滤，过滤规则有rule_weight, rule_blacklist，返回ruleAction
2. 得到ruleAction之后，会使用initializer，确定奖品分布（有两种，一种是没有rule_weight,一种是有rule_weight）
3. 调用executor直接进行抽奖（生成随机数）


 
 

### Mybatis IndexOutOfBoundsException
    Caused by: org.apache.ibatis.exceptions.PersistenceException:
    Error querying database.  Cause: java.lang.IndexOutOfBoundsException: Index: 6, Size: 6

There must be a constructor without parameter


### NullPointer when dependency injection @Resource / @Autowire in @Component

In spring, manually initializing BlackListFilter class can cause iStrategyRepo to be NullPointer. 
When you manually instantiate the class using new BlackListFilter(), Spring's dependency injection mechanism is bypassed, and the iStrategyRepo dependency is not injected.