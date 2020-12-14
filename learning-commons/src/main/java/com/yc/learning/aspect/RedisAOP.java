package com.yc.learning.aspect;

import com.yc.learning.annotaion.RedisAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class RedisAOP {
    @Autowired
    private RedisTemplate redisTemplate;
    private static Logger logger = LoggerFactory.getLogger(RedisAOP.class.getName());

    @Pointcut("@annotation(com.yc.learning.annotaion.RedisAnnotation)")
    public void useRedis(){


    }
    @Around("useRedis()")
    public Object around(ProceedingJoinPoint  joinPoint) throws Throwable {
        //前置增强
        //方法名字
        String methodName=joinPoint.getSignature().getName();
        //方法参数
//        Object[] args = joinPoint.getArgs(); // 参数值
//
//        String[] argNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames(); // 参数名


        // 参数值

        String value = Arrays.toString(joinPoint.getArgs());
        //参数名字
        String args =Arrays.toString(((MethodSignature)joinPoint.getSignature()).getParameterNames());// 参数名

        //代理对象
        Class<?> aClass = joinPoint.getTarget().getClass();
        //class名字
        String className =aClass.getSimpleName();
//        System.out.println("方法名字："+methodName);
        Signature sig = joinPoint.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = joinPoint.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        RedisAnnotation annotation = currentMethod.getAnnotation(RedisAnnotation.class);
//        System.out.println(annotation);
        //获得该注释需要搞什么
        System.out.println("是否使用缓存"+annotation.useRedis()+"   是否删除缓存"+annotation.deleteRedis()+"    " +
                "是否更新缓存"+annotation.updateRedis());


        //获得key必须要获得类名+方法名+args
        String key = className+args+"="+value;

        System.out.println(key);

        Object proceed=null;
        if(annotation.useRedis()==true){
            proceed= PutCachRedis(key, joinPoint);
            return proceed;
        }
        else if(annotation.updateRedis()==true){
             proceed = updateRedis(key, joinPoint);
        }
        else if(annotation.deleteRedis()==true){
            proceed= deleteRedis(key,  joinPoint);
        }





//        System.out.println( Arrays.toString(argNames));
//        System.out.println( Arrays.toString(args));
        return proceed;
    }
    //删除缓存
    private Object deleteRedis(String key, ProceedingJoinPoint joinPoint) throws Throwable {
        Boolean hasKey = redisTemplate.hasKey(key);
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        if(hasKey){
            System.out.println("进行删除缓存操作");
            redisTemplate.delete(key);
        }
        Object proceed =joinPoint.proceed();
        System.out.println("删除缓存成功！！");
        return proceed;
    }


    //updateRedis
    private Object updateRedis(String key, ProceedingJoinPoint joinPoint) throws Throwable {
        Boolean hasKey = redisTemplate.hasKey(key);
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        if(hasKey){
            System.out.println("进行更新缓存操作");
            redisTemplate.delete(key);
        }

        Object proceed =joinPoint.proceed();
        //将查询的对象存入redis缓存
        operations.set(key,proceed,60,TimeUnit.MINUTES );

        System.out.println("缓存更新操作更新成功");
        return proceed;
    }


    //useRedis
    private Object PutCachRedis(String key,ProceedingJoinPoint  joinPoint) throws Throwable {
        Boolean hasKey = redisTemplate.hasKey(key);
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();

        if(hasKey){
            System.out.println("从缓存中取出");
            return (Object) operations.get(key);
        }

        Object proceed =joinPoint.proceed();
        //将查询的对象存入redis缓存
        operations.set(key,proceed,60,TimeUnit.MINUTES );
        System.out.println("加入缓存"+proceed);
        return proceed;
    }


}
