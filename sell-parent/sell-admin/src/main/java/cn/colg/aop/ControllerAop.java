package cn.colg.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import cn.colg.exception.CheckException;
import cn.colg.vo.ResultVO;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * aop 处理、包装异常
 *
 * @author colg
 */
@Slf4j
@Aspect
@Component
public class ControllerAop {

    @Pointcut("execution(public cn.colg.vo.ResultVO *(..))")
    private void controllerMethod() {}

    @Around("controllerMethod()")
    public ResultVO handlerControllerMethod(ProceedingJoinPoint point) {
        long startTime = System.currentTimeMillis();

        ResultVO resultVO = new ResultVO();
        try {
            // 执行目标方法
            resultVO = (ResultVO)point.proceed();
            log.info(point.getSignature() + " USE TIME: [{}ms]", DateUtil.spendMs(startTime));
        } catch (Throwable e) {
            // 执行目标方法异常，包装异常
            resultVO = handlerException(point, e);
        }
        return resultVO;
    }

    /**
     * 包装异常信息
     *
     * @param point
     * @param e
     * @return
     */
    private ResultVO handlerException(ProceedingJoinPoint point, Throwable e) {
        ResultVO resultVO = new ResultVO();

        // 已知异常
        if (e instanceof CheckException || e instanceof IllegalArgumentException) {
            // 校验出错，参数非法
            resultVO.setMsg(e.getLocalizedMessage());
            resultVO.setCode(ResultVO.CHECK_FAIL);
        }
        // 未知异常
        else {
            log.error(point.getSignature() + " ERROR {}", e);
            resultVO = new ResultVO(e);
        }

        return resultVO;
    }
}
