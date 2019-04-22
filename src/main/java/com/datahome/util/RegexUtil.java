package com.datahome.util;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/1/9 14:18
 */
public class RegexUtil {

    public static final String ID = "^[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}$";

    public static final String SEX = "^(1|2)$";

    public static final String EDUCATION = "^(1|2|3|4)$";

    public static final String COMMON_FLAG_CODE = "^(1|2|3)$";

    public static final String PHONE = "^1[3578]\\d{9}$";

    public static final String PASSWORD = "^[a-zA-Z0-9]{5,20}$";

    public static final String ORDER = "^(ASC|DESC)$";

    public static final String COMMON_STATUS_CODE = "^(1|2)$";

    public static final String IDCARD = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
}
