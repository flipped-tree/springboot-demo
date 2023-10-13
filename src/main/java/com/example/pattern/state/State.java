package com.example.pattern.state;

public interface State {
    void doClock(Context context, int hour);    // 设置时间

    void doUse(Context context);                // 使用金库

    void doAlarm(Context context);              // 按下警铃

    void doPhone(Context context);              // 正常通话
}
