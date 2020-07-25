package com.learn.jeffrey.utils.exception;

import lombok.Getter;

/**
 * Description <P>异常处理工具类</P>
 *
 * @Author lijianfei
 * @Date 2020/7/20 10:48
 **/
public class ExceptionUtils {
    @Getter
    public static class BaseRuntimeException extends RuntimeException {

        private String errorCode;
        private String[] args;

        public BaseRuntimeException(Throwable e, String errorCode, String... args) {
            this.errorCode = errorCode;
            this.args = args;
        }
    }

    public static class ModelException extends BaseRuntimeException {
        public ModelException(Throwable e, String errorCode, String... args) {
            super(e, errorCode, args);
        }
    }

    public static class AppException extends BaseRuntimeException {
        public AppException(Throwable e, String errorCode, String... args) {
            super(e, errorCode, args);
        }
    }

    public static class FatalException extends BaseRuntimeException {
        public FatalException(Throwable e, String errorCode, String... args) {
            super(e, errorCode, args);
        }
    }

    /**
     * api接口调用异常
     */
    public static class ApiException extends BaseRuntimeException {
        public ApiException(Throwable e, String errorCode, String... args) {
            super(e, errorCode, args);
        }
    }

    /**
     * Service层异常处理方法
     *
     * @param e         异常堆栈信息
     * @param errorCode 数据异常码
     * @param args      异常信息点位符
     */
    public static void newModelException(Throwable e, String errorCode, String... args) {
        throw new ModelException(e, errorCode, args);
    }

    /**
     * @param errorCode
     */
    public static void newModelException(String errorCode) {
        new ModelException(null, errorCode, new String[]{});
    }

    /**
     * 异常生成
     *
     * @param errorCode
     * @param args
     * @return ModelException
     */
    public static ModelException createModelException(String errorCode, String... args) {
        return new ModelException(null, errorCode, args);
    }
}
