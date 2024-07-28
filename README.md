#

### Mybatis IndexOutOfBoundsException
    Caused by: org.apache.ibatis.exceptions.PersistenceException:
    Error querying database.  Cause: java.lang.IndexOutOfBoundsException: Index: 6, Size: 6

There must be a constructor without parameter


### NullPointer when dependency injection @Resource / @Autowire in @Component

In spring, @Resource / @Autowire happens before @Component, so it will cause NullPointer error