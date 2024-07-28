#

### Mybatis IndexOutOfBoundsException
    Caused by: org.apache.ibatis.exceptions.PersistenceException:
    Error querying database.  Cause: java.lang.IndexOutOfBoundsException: Index: 6, Size: 6

There must be a constructor without parameter


### NullPointer when dependency injection @Resource / @Autowire in @Component

In spring, manually initializing BlackListFilter class can cause iStrategyRepo to be NullPointer. 
When you manually instantiate the class using new BlackListFilter(), Spring's dependency injection mechanism is bypassed, and the iStrategyRepo dependency is not injected.