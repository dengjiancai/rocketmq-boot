package com.ruby.rocketmq.demo.utils;

import com.ruby.rocketmq.demo.utils.constant.ResponseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.util.Optional;


/**
 * @author
 * @version 1.0
 * @date
 * @description 通用返回对象
 **/
@Getter
@Setter
@ToString
@ApiModel
public class ResMsg<T> {

    /**
     * 系统处理标识
     */
    @ApiModelProperty(required = true, example = "true", value = "系统处理标识 true：成功 false：失败")
    private boolean success;

    /**
     * 业务处理 成功/失败 编码
     */
    @ApiModelProperty(required = true, example = "0000", value = "业务处理成功/失败编码")
    private String code;

    /**
     * 业务处理 成功/失败 提示信息
     */
    @ApiModelProperty(required = true, example = "业务处理成功", value = "状态码所对应的描述")
    private String message;

    /**
     * 业务处理成功返回的对象
     */
    @ApiModelProperty(value = "业务返回的实体对象")
    private T result;

    private ResMsg(boolean success, String code, String message, T result) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.result = result;
    }

    private ResMsg(boolean success, ResponseEnum responseEnum, T result) {
        this.success = success;
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
        this.result = result;
    }

    /**
     * 业务处理成功：没有对象，只有成功提示信息与编码
     *
     * @return
     */
    public static ResMsg success() {
        return new ResMsg(true, ResponseEnum.SUCCESS, null);
    }

    /**
     * 业务处理成功：没有对象，返回默认编码与自定义成功提示信息
     *
     * @param message
     * @return
     */
    public static ResMsg success(String message) {
        message = StringUtils.isEmpty(message) ? ResponseEnum.SUCCESS.getMessage() : message;
        return new ResMsg(true, ResponseEnum.SUCCESS.getCode(), message, null);
    }

    /**
     * 业务处理成功：有对象，返回默认成功提示信息与编码
     *
     * @param result
     * @param <T>
     * @return
     */
    public static <T> ResMsg<T> success(T result) {
        return new ResMsg(true, ResponseEnum.SUCCESS, result);
    }

    /**
     * 业务处理成功：有对象，返回默认编码与自定义提示信息
     *
     * @param result
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResMsg<T> success(T result, String message) {
        message = StringUtils.isEmpty(message) ? ResponseEnum.SUCCESS.getMessage() : message;
        return new ResMsg(true, ResponseEnum.SUCCESS.getCode(), message, result);
    }

    /**
     * 业务处理失败
     * @return
     */
    public static ResMsg busiFailed(){
        return new ResMsg(true, ResponseEnum.BUSI_FAILED_USER_UNKNOWN.getCode(), ResponseEnum.BUSI_FAILED_USER_UNKNOWN.getMessage(), null);
    }

    /**
     * 业务处理失败：自定义code， 自定义message，无对象
     * @param code
     * @param message
     * @return
     */
    public static ResMsg busiFailed(String code, String message){
        code = StringUtils.isEmpty(code) ? ResponseEnum.BUSI_FAILED_USER_UNKNOWN.getCode() : code;
        message = StringUtils.isEmpty(message) ? ResponseEnum.BUSI_FAILED_USER_UNKNOWN.getMessage() : message;
        return new ResMsg(true, code, message, null);
    }

    /**
     * 业务处理失败：自定义code， 自定义message，有对象
     * @param result
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResMsg<T> busiFailed(T result, String code, String message){
        code = StringUtils.isEmpty(code) ? ResponseEnum.BUSI_FAILED_USER_UNKNOWN.getCode() : code;
        message = StringUtils.isEmpty(message) ? ResponseEnum.BUSI_FAILED_USER_UNKNOWN.getMessage() : message;
        return new ResMsg(true, code, message, result);
    }

    /**
     * 系统处理失败：系统内部处理失败：未知错误
     *
     * @return
     */
    public static ResMsg failed() {
        return new ResMsg(false, ResponseEnum.ERROR_UNKNOWN, null);
    }

    /**
     * 系统处理失败：枚举异常信息与提示码
     *
     * @param responseEnum
     * @return
     */
    public static ResMsg failed(ResponseEnum responseEnum) {
        responseEnum = Optional.ofNullable(responseEnum).orElse(ResponseEnum.ERROR_UNKNOWN);
        return new ResMsg(false, responseEnum, null);
    }

    /**
     * 系统处理失败：自定义异常信息与自定义提示码，但是会将提示码与ResponseEnum中的code进行匹配，如果匹配不到，依旧返回未知错误编码9999
     * @param code
     * @param message
     * @return
     */
    public static ResMsg failed(String code, String message) {
        code = StringUtils.isEmpty(code) ? ResponseEnum.ERROR_UNKNOWN.getCode() : ResponseEnum.codeMatch(code);
        message = StringUtils.isEmpty(message) ? ResponseEnum.codeMessage(code) : message;

        return new ResMsg(false, code, message, null);
    }

    /**
     * 系统处理失败：自定义异常信息，固定枚举编码
     *
     * @param responseEnum
     * @param message
     * @return
     */
    public static ResMsg failed(ResponseEnum responseEnum, String message) {
        responseEnum = Optional.ofNullable(responseEnum).orElse(ResponseEnum.ERROR_UNKNOWN);
        message = StringUtils.isEmpty(message) ? ResponseEnum.ERROR_UNKNOWN.getMessage() : message;

        return new ResMsg(false, responseEnum.getCode(), message, null);
    }

}
