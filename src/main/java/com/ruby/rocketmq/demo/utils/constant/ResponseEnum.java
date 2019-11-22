package com.ruby.rocketmq.demo.utils.constant;

/**
 * @author
 * @version 1.0
 * @date
 * @description 响应状态枚举，如果需要增加相应的状态群请标明注释
 **/
public enum ResponseEnum {

    /**
     * 0000代表业务处理成功
     */
    SUCCESS("0000", "处理成功"),

    //----------------业务处理失败：0001-0999代码属于系统处理成功，但业务处理失败——BusinessException----------------（success=true）//
    /**
     *
     */
    BUSI_FAILED_USER_LOGIN("0001", "登录失败，用户名或密码不正确"),
    BUSI_FAILED_USER_UNKNOWN("0999", "业务处理失败，原因未知"),

    BUSI_FAILED_INCORREDCT_ACCOUNT_OR_CREDENTIALS("0002", "账号/密码错误"),
    BUSI_FAILED_NOT_LOGGED_IN("0003", "未登录"),
    BUSI_FAILED_CURRENT_USER_NOT_EXIST("0004", "当前无登录用户信息"),
    BUSI_FAILED_USER_ID_NOT_EXIST("0005", "用户ID不存在"),
    BUSI_FAILED_RESOURCE_TYPE_NOE_EXIST("0006", "菜单类型不存在"),
    BUSI_FAILED_MENU_ID_NOT_EXIST("0007", "菜单ID不存在"),
    BUSI_FAILED_ROLE_ID_NOT_EXIST("0008", "角色ID不存在"),
    BUSI_FAILED_PASS_NOT_CONTAIN_NUM_OR_CHAR_OR_LESS_SIX("0009", "密码必须是六个字符以上，并且包含数字、字母"),
    BUSI_FAILED_PASSWORD_CAN_NOT_EMPTY("0010", "原密码不能为空"),
    BUSI_FAILED_UNAUTHORIZED_MODIFY_THAT_ID("0011", "无权修改此ID密码"),
    BUSI_FAILED_USERNAME_REPEAT("0012", "用户账号已存在"),
    BUSI_FAILED_MOBILE_REPEAT("0013", "手机号已被使用"),
    BUSI_FAILED_EMAIL_REPEAT("0014", "邮箱已被使用"),
    BUSI_FAILED_MOBILE_ERROR("0015", "手机号验证失败"),
    BUSI_FAILED_USER_CAN_NOT_OPERATION("0016", "操作失败，该用户已被注销"),
    BUSI_FAILED_FAILED_PARAM_CAPTCHA_NULL("0017", "验证码不能为空"),
    BUSI_FAILED_FAILED_PARAM_CAPTCHA_ERROR("0018", "验证码错误"),
    BUSI_FAILED_AUTHENTICATED_FAILED("0018", "身份认证失败"),
    BUSI_FAILED_FAILED_PARAM_CAPTCHA_GET_ERROR("0018", "获取验证码失败"),
    BUSI_FAILED_ACCOUNT_LOCKED("0018", " 账号已被禁用"),
    BUSI_FAILED_FAILED_PARAM_EXCESSIVE_ATTEMPTS("0018", "尝试登录失败次数过多"),
    BUSI_FAILED_AUTHORIZED_FAILED("0018", "用户权限认证失败"),
    BUSI_FAILED_USER_STATE_MISMATCH("0019", "用户状态不匹配"),
    BUSI_FAILED_CURRENT_USER_INFOR_ERROR_COMPID("0020", "当前用户的企业ID信息错误，查询企业信息异常"),
    BUSI_FAILED_CURRENT_USER_INFOR_ERROR_NOT_CHANGE_MGR("0021", "用户没有申请变更管理员请求"),
    BUSI_FAILED_CURRENT_USER_INFOR_ERROR_TO_MACH_CHANGE_MGR("0022", "数据异常，当前用户申请变更管理员请求大于一个"),
    BUSI_FAILED_AUDIT_STATE_ERROR("0023", "审核结果码识别失败"),

    BUSI_FAILED_TOKEN_VERIFY_FAILD("0024", "token验证失败"),
    BUSI_FAILED_REDIS_TOKEN_ERROR("0025", "redis中保存的token信息错误，请联系管理员检查"),
    BUSI_FAILED_JGQ_PARAM_ERROR_PASS("0026", "修改密码不能为空"),
    BUSI_FAILED_JGQ_PARAM_ERROR_STATUS("0027", "修改状态不能为空"),


    BUSI_FAILED_BANK_FILE_TOKEN_ERROR("0028","银行文件下载token不存在"),
    BUSI_FAILED_FILEID_ERROR("0029","下载文件id错误"),
    BUSI_FAILED_PAYROLL_ROLE_ERROR("0030","工资代发系统用户角色错误"),

    BUSI_FAILED_FILE_NULL("0031","文件为空"),
    BUSI_FAILED_USER_STATE_UNAVAILABLE("0032", "用户当前状态不可用"),
    BUSI_FAILED_USER_HAD_OPEN_JGQ("0033", "当前用户已经开通过建工券，不可重复开通"),
    BUSI_FAILED_USER_OPEN_JGQ_AUTH_FAILD("0033", "权限认证失败，不可开通"),
    BUSI_FAILED_GET_TOKEN_PRODUCT_ERROR("0034", "请求Token的产品服务不存在"),
    BUSI_FAILED_FINANCE_MGR_CHANGE_RECORD_ERROR("0035", "管理员变更申请数据异常（变更请求数量大于1）"),
    BUSI_FAILED_FILE_DECRYPT("0036","文件解密失败"),

    CHECK_FAILED_PAYROLL_UN_AUDIT("0037","核对不通过，该工资单未审核"),
    CHECK_FAILED_PAYROLL_UN_PAY("0038","核对不通过，该工资单未发放"),
    DOWNLOAD_FAILED_PAYROLL_UN_AUDIT("0039","银行文件下载失败，工资单未审核通过"),
    BUSI_FAILED_UPLOAD_FILE_TYPE_ERROR("0040","上传的文件类型错误"),
    BUSI_FAILED_ENTERPRISE_USCC_TOO_MUCH("0041","企业统一社会信用代码重复，请联系管理员"),
    BUSI_FAILED_EXCEL_EXPORT("0042", "查询没有值，无法导出Excel"),

    BUSI_FAILED_LOGIN_TYPE_ERROR("0043", "登录类型错误"),
    BUSI_FAILED_COMP_NAME_ERROR("0044", "企业名称错误"),
    BUSI_FAILED_COMP_USCC_CODE_ERROR("0044", "企业统一社会信用代码错误"),
    BUSI_FAILED_USER_STATE_EXCEPTION("0045", "用户状态异常，请联系管理员"),
    BUSI_FAILED_DOWNLOAC_AUTHORIZED_FAILED("0046", "权限认证失败，无权限下载该文件"),
    BUSI_FAILED_FINANCE_MGR_REPEAT("0047", "财务负责人创建失败，该企业已创建过财务负责人"),
    BUSI_FAILED_ACC_NOT_EXIST("0048", "发薪企业未在桂建金服注册"),




    //----------------系统处理失败：1001-1999错误代码产生的错误代码属于正常容错，不需要记录异常堆栈，但需要记录错误信息——ProcessException（success=false）----------------//
    /**
     * 1000-1999编码群：代表客户端发送的请求错误，对请求的数据校验如（参数验证、查询验证、插入验证等）失败
     */
    FAILED_PARAM("1001", "参数校验不通过"),
    FAILED_EMPTY_DATA("1002", "未查询到相关数据"),
    FAILED_DATA_EXIST("1003", "已存在相关数据"),
    FAILED_REQUEST_REPEAT("1004", "客户端请求重复"),
    FAILED_REQUEST_METHOD_NOT_SUPPORTED("1005", "不支持的请求类型"),
    FAILED_PARAM_SIGN("1006", "签名验证失败"),
    FAILED_CAPTCHA_TYPE_ERR("1007","验证码类型错误"),
    FAILED_PARAM_COMP_ID("1008","企业ID格式错误（应全为数字）"),


    //----------------系统处理失败：8001-9999错误代码产生错误代属于严重错误，需要记录异常堆栈，需要记录错误信息——ProcessException（success=false）----------------//
    /**
     * 8000-8999编码群：代表本服务内部无错误，但是本服务访问其他第三方服务出现失败
     */
    FAILED_SERVICE_INVOKE("8001", "请求远程服务失败"),
    FAILED_SERVICE_NOT_RESPONSE("8002", "请求远程服务超时"),
    FAILED_SERVICE_DEAL("8003", "远程服务业务处理失败"),
    FAILED_SERVICE_NULL_RESPONSE("8004", "远程服务返回报文为空"),
    FAILED_SERVICE_ERROR_RESPONSE("8005", "远程服务返回报文格式不正确"),
    FAILED_SERVICE_PARAM_VALIDATED_FAILED("8006", "调用远程服务返回参数校验失败"),
    FAILED_SERVICE_UNKNOWN("8999", "调用远程服务出错，原因未知"),

    /**
     * 9000-9999编码群：代表系统内部出错
     */
    ERROR_DB_QUERY("9001", "数据查询失败"),
    ERROR_DB_INSERT("9002", "数据插入失败"),
    ERROR_DB_UPDATE("9003", "数据更新失败"),
    ERROR_DB_DELETE("9003", "数据删除失败"),
    ERROR_NOSUCH_ELEM("9004", "没有对应的必须存在的对象"),
    ERROR_MONGO_UPLOAD("9005","文件上传mongo失败"),
    ERROR_MONGO_DOWNLOAD("9006","mongo下载文件失败"),
    ERROR_AES("9007","AES加解密异常"),

    ERROR_UNKNOWN("9999", "未知错误"),

    ;
    private String code;
    private String message;

    ResponseEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 匹配编码所对应的提示信息
     * @param code
     * @return
     */
    public static String codeMessage(String code){
        for(ResponseEnum resEnum : ResponseEnum.values()){
            if(resEnum.getCode().equals(code)){
                return resEnum.getMessage();
            }
        }
        return ResponseEnum.ERROR_UNKNOWN.getMessage();
    }

    /**
     * 匹配编码所对应的枚举
     * @param code
     * @return
     */
    public static ResponseEnum codeEnum(String code){
        for(ResponseEnum resEnum : ResponseEnum.values()){
            if(resEnum.getCode().equals(code)){
                return resEnum;
            }
        }
        return ResponseEnum.ERROR_UNKNOWN;
    }

    /**
     * 匹配外部编码
     * @param outerCode
     * @return
     */
    public static String codeMatch(String outerCode){
        for(ResponseEnum resEnum : ResponseEnum.values()){
            if(resEnum.getCode().equals(outerCode)){
                return resEnum.getCode();
            }
        }
        return ResponseEnum.ERROR_UNKNOWN.getCode();
    }

    /**
     * 根据编码，判断是否需要记录日志堆栈，在8000之后的需要记录堆栈，8000之前的不需要记录日志堆栈
     * @param outerCode
     * @return
     */
    public static boolean codeLogStack(String outerCode){
        boolean isLogStack = true;

        for(ResponseEnum resEnum : ResponseEnum.values()){
            if(resEnum.getCode().equals(outerCode)){
                int code = Integer.valueOf(resEnum.getCode());
                if(code < 8000){
                    isLogStack = false;
                }
            }
        }
        return isLogStack;
    }

}
