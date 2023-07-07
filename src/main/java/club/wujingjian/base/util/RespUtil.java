package club.wujingjian.base.util;

import club.wujingjian.base.vo.BaseResp;

public class RespUtil {
    public static <T> BaseResp success(T data) {
        return new BaseResp(data, "200", null);
    }

    public static <T> BaseResp fail(String msg) {
        return new BaseResp("", "500", msg);
    }
}
