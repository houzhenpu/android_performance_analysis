package com.performance.analysis.kit.timecounter.instrumentation;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.performance.analysis.kit.timecounter.TimeCounterManager;
import com.performance.analysis.util.Reflector;

class ProxyHandlerCallback implements Handler.Callback {

    private static final int LAUNCH_ACTIVITY = 100;
    private static final int PAUSE_ACTIVITY = 101;
    private static final int EXECUTE_TRANSACTION = 159;
    private static final String LAUNCH_ITEM_CLASS = "android.app.servertransaction.ResumeActivityItem";
    private static final String PAUSE_ITEM_CLASS = "android.app.servertransaction.PauseActivityItem";

    private final Handler.Callback mOldCallback;
    public final Handler mHandler;

    ProxyHandlerCallback(Handler.Callback oldCallback, Handler handler) {
        mOldCallback = oldCallback;
        mHandler = handler;
    }

    @Override
    public boolean handleMessage(Message msg) {
        // 处理消息开始，同时返回消息类型，主要为了兼容Android P，把159消息转为101(Pause)和100(Launch)
        int msgType = preDispatch(msg);
        // 如果旧的callback返回true,表示已经被它拦截，而它内部必定调用了Handler.handleMessage,直接返回
        if (mOldCallback != null && mOldCallback.handleMessage(msg)) {
            postDispatch(msgType);
            return true;
        }
        // 直接调用handleMessage执行消息处理
        mHandler.handleMessage(msg);
        // 处理消息结束
        postDispatch(msgType);
        // 返回true,表示callback会拦截消息，Hanlder不需要再处理消息因为我们上一步已经处理过了
        return true;
    }

    private int preDispatch(Message msg) {
        switch (msg.what) {
            case LAUNCH_ACTIVITY:
                TimeCounterManager.get().onActivityLaunch();
                break;
            case PAUSE_ACTIVITY:
                TimeCounterManager.get().onActivityPause();
                break;
            case EXECUTE_TRANSACTION:
                return handlerActivity(msg);
            default:
                break;
        }
        return msg.what;
    }

    private int handlerActivity(Message msg) {
        Object obj = msg.obj;

        Object activityCallback = Reflector.QuietReflector.with(obj).method("getLifecycleStateRequest").call();
        if (activityCallback != null) {
            String transactionName = activityCallback.getClass().getCanonicalName();
            if (TextUtils.equals(transactionName, LAUNCH_ITEM_CLASS)) {
                TimeCounterManager.get().onActivityLaunch();
                return LAUNCH_ACTIVITY;
            } else if (TextUtils.equals(transactionName, PAUSE_ITEM_CLASS)) {
                TimeCounterManager.get().onActivityPause();
                return PAUSE_ACTIVITY;
            }
        }
        return msg.what;
    }

    private void postDispatch(int msgType) {
        switch (msgType) {
            case LAUNCH_ACTIVITY:
                TimeCounterManager.get().onActivityLaunched();
                break;
            case PAUSE_ACTIVITY:
                TimeCounterManager.get().onActivityPaused();
                break;
            default:
                break;
        }
    }
}
