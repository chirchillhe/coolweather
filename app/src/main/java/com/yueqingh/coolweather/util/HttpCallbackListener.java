package com.yueqingh.coolweather.util;

/**
 * @author: yueqi
 * @date: 2022/10/22
 */
public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
