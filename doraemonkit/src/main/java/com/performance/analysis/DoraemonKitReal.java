package com.performance.analysis;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.didichuxing.doraemonkit.R;
import com.performance.analysis.constant.DokitConstant;
import com.performance.analysis.constant.SharedPrefsKey;
import com.performance.analysis.kit.Category;
import com.performance.analysis.kit.IKit;
import com.performance.analysis.kit.alignruler.AlignRulerKit;
import com.performance.analysis.kit.blockmonitor.BlockMonitorKit;
import com.performance.analysis.kit.colorpick.ColorPickerKit;
import com.performance.analysis.kit.dataclean.DataCleanKit;
import com.performance.analysis.kit.layoutborder.LayoutBorderKit;
import com.performance.analysis.kit.mode.FloatModeKit;
import com.performance.analysis.kit.parameter.cpu.CpuKit;
import com.performance.analysis.kit.parameter.frameInfo.FrameInfoKit;
import com.performance.analysis.kit.parameter.ram.RamKit;
import com.performance.analysis.kit.sysinfo.SysInfoKit;
import com.performance.analysis.kit.temporaryclose.TemporaryCloseKit;
import com.performance.analysis.kit.timecounter.TimeCounterKit;
import com.performance.analysis.kit.timecounter.instrumentation.HandlerHooker;
import com.performance.analysis.kit.uiperformance.UIPerformanceKit;
import com.performance.analysis.kit.version.DokitVersionKit;
import com.performance.analysis.kit.viewcheck.ViewCheckerKit;
import com.performance.analysis.ui.UniversalActivity;
import com.performance.analysis.ui.base.AbsDokitView;
import com.performance.analysis.ui.base.DokitIntent;
import com.performance.analysis.ui.base.DokitViewManager;
import com.performance.analysis.ui.main.FloatIconDokitView;
import com.performance.analysis.ui.main.ToolPanelDokitView;
import com.performance.analysis.util.LifecycleListenerUtil;
import com.performance.analysis.util.LogHelper;
import com.performance.analysis.util.PermissionUtil;
import com.performance.analysis.util.SharedPrefsUtil;
import com.performance.analysis.util.UIUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

/**
 * DoraemonKit 真正执行的类  不建议外部app调用
 */
class DoraemonKitReal {
    private static final String TAG = "DoraemonKitReal";


    private static boolean sHasRequestPermission;

    private static boolean sHasInit = false;

    /**
     * 是否允许上传统计信息
     */
    private static boolean sEnableUpload = true;
    private static Application APPLICATION;

    /**
     * 用来判断是否接入了dokit插件 如果安装了插件会动态修改这个值为true
     */
    private static boolean IS_HOOK = false;
    /**
     * fragment 生命周期回调
     */
    private static FragmentManager.FragmentLifecycleCallbacks sFragmentLifecycleCallbacks = new FragmentManager.FragmentLifecycleCallbacks() {
        @Override
        public void onFragmentAttached(FragmentManager fm, Fragment fragment, Context context) {
            super.onFragmentAttached(fm, fragment, context);
            LogHelper.d(TAG, "onFragmentAttached: " + fragment);
            for (LifecycleListenerUtil.LifecycleListener listener : LifecycleListenerUtil.LIFECYCLE_LISTENERS) {
                listener.onFragmentAttached(fragment);
            }
        }

        @Override
        public void onFragmentDetached(FragmentManager fm, Fragment fragment) {
            super.onFragmentDetached(fm, fragment);
            LogHelper.d(TAG, "onFragmentDetached: " + fragment);
            for (LifecycleListenerUtil.LifecycleListener listener : LifecycleListenerUtil.LIFECYCLE_LISTENERS) {
                listener.onFragmentDetached(fragment);
            }
        }
    };

    static void setDebug(boolean debug) {
        LogHelper.setDebug(debug);
    }


    static void install(Application app) {
        install(app, null);
    }

    static void install(Application app, List<IKit> selfKits) {
        install(app, selfKits, "");
    }

    /**
     * @param app
     * @param selfKits  自定义kits
     * @param productId Dokit平台端申请的productId
     */
    static void install(final Application app, List<IKit> selfKits, String productId) {
        DokitConstant.PRODUCT_ID = productId;
        //添加常用工具
        if (sHasInit) {
            //已经初始化添加自定义kits
            if (selfKits != null) {
                List<IKit> biz = DokitConstant.KIT_MAPS.get(Category.BIZ);
                if (biz != null) {
                    biz.clear();
                    biz.addAll(selfKits);
                    for (IKit kit : biz) {
                        kit.onAppInit(app);
                    }
                }
            }
            //aop会再次注入一遍 所以需要直接返回
            return;
        }
        sHasInit = true;
        //赋值
        APPLICATION = app;
        String strfloatMode = SharedPrefsUtil.getString(app, SharedPrefsKey.FLOAT_START_MODE, "normal");
        if (strfloatMode.equals("normal")) {
            DokitConstant.IS_NORMAL_FLOAT_MODE = true;
        } else {
            DokitConstant.IS_NORMAL_FLOAT_MODE = false;
        }

        //解锁系统隐藏api限制权限以及hook Instrumentation
        HandlerHooker.doHook(app);

        LogHelper.i(TAG, "IS_HOOK====>" + IS_HOOK);
        //注册全局的activity生命周期回调
        app.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            int startedActivityCounts;

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                if (ignoreCurrentActivityDokitView(activity)) {
                    return;
                }
                if (activity instanceof FragmentActivity) {
                    //注册fragment生命周期回调
                    ((FragmentActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(sFragmentLifecycleCallbacks, true);
                }

            }

            @Override
            public void onActivityStarted(Activity activity) {
                if (ignoreCurrentActivityDokitView(activity)) {
                    return;
                }
                if (startedActivityCounts == 0) {
                    DokitViewManager.getInstance().notifyForeground();

                }
                startedActivityCounts++;
            }

            /**
             * 当activity进入可交互状态
             * @param activity
             */
            @Override
            public void onActivityResumed(Activity activity) {
                //如果是leakCanary页面不进行添加
                if (ignoreCurrentActivityDokitView(activity)) {
                    return;
                }

                //设置app的直接子view的Id
                if (UIUtils.getDokitAppContentView(activity) != null) {
                    UIUtils.getDokitAppContentView(activity).setId(R.id.dokit_app_contentview_id);
                }


                if (DokitConstant.IS_NORMAL_FLOAT_MODE) {
                    //显示内置dokitView icon
                    resumeAndAttachDokitViews(activity);
                } else {
                    //悬浮窗权限 vivo 华为可以不需要动态权限 小米需要
                    if (PermissionUtil.canDrawOverlays(activity)) {
                        //系统悬浮窗需要判断浮标是否已经显示
                        if (!DokitConstant.MAIN_ICON_HAS_SHOW) {
                            showSystemMainIcon();
                        }
                        systemDokitViewOnResume(activity);
                    } else {
                        //请求悬浮窗权限
                        requestPermission(activity);
                    }
                }

                for (LifecycleListenerUtil.LifecycleListener listener : LifecycleListenerUtil.LIFECYCLE_LISTENERS) {
                    listener.onActivityResumed(activity);
                }
            }

            @Override
            public void onActivityPaused(Activity activity) {
                if (ignoreCurrentActivityDokitView(activity)) {
                    return;
                }
                for (LifecycleListenerUtil.LifecycleListener listener : LifecycleListenerUtil.LIFECYCLE_LISTENERS) {
                    listener.onActivityPaused(activity);
                }

                //sCurrentResumedActivity = null;
            }

            @Override
            public void onActivityStopped(Activity activity) {
                if (ignoreCurrentActivityDokitView(activity)) {
                    return;
                }
                startedActivityCounts--;
                //通知app退出到后台
                if (startedActivityCounts == 0) {
                    DokitViewManager.getInstance().notifyBackground();

                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                if (ignoreCurrentActivityDokitView(activity)) {
                    return;
                }
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                if (ignoreCurrentActivityDokitView(activity)) {
                    return;
                }
                //注销fragment的生命周期回调
                if (activity instanceof FragmentActivity) {
                    ((FragmentActivity) activity).getSupportFragmentManager().unregisterFragmentLifecycleCallbacks(sFragmentLifecycleCallbacks);
                }
                DokitViewManager.getInstance().onActivityDestroy(activity);
            }
        });
        DokitConstant.KIT_MAPS.clear();

        //业务专区
        List<IKit> biz = new ArrayList<>();
        //weex专区
        List<IKit> weex = new ArrayList<>();

        //常用工具
        List<IKit> tool = new ArrayList<>();
        //性能监控
        List<IKit> performance = new ArrayList<>();
        //视觉工具
        List<IKit> ui = new ArrayList<>();
        //平台工具
        List<IKit> platform = new ArrayList<>();
        //悬浮窗模式
        List<IKit> floatMode = new ArrayList<>();
        //退出
        List<IKit> exit = new ArrayList<>();
        //版本号
        List<IKit> version = new ArrayList<>();
        //添加工具kit
        tool.add(new SysInfoKit());
        tool.add(new DataCleanKit());

        //添加性能监控kit
        performance.add(new FrameInfoKit());
        performance.add(new CpuKit());
        performance.add(new RamKit());
        performance.add(new BlockMonitorKit());
        performance.add(new TimeCounterKit());
        //performance.add(new MethodCostKit());
        performance.add(new UIPerformanceKit());

        //添加视觉ui kit
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ui.add(new ColorPickerKit());
        }

        ui.add(new AlignRulerKit());
        ui.add(new ViewCheckerKit());
        ui.add(new LayoutBorderKit());

        //增加浮标模式
        floatMode.add(new FloatModeKit());
        //添加退出项
        exit.add(new TemporaryCloseKit());
        //添加版本号项
        version.add(new DokitVersionKit());
        //添加自定义
        if (selfKits != null && !selfKits.isEmpty()) {
            biz.addAll(selfKits);
        }
        //调用kit 初始化
        for (IKit kit : biz) {
            kit.onAppInit(app);
        }
        for (IKit kit : performance) {
            kit.onAppInit(app);
        }
        for (IKit kit : tool) {
            kit.onAppInit(app);
        }
        for (IKit kit : ui) {
            kit.onAppInit(app);
        }
        //注入到sKitMap中
        DokitConstant.KIT_MAPS.put(Category.BIZ, biz);

        DokitConstant.KIT_MAPS.put(Category.PERFORMANCE, performance);
        DokitConstant.KIT_MAPS.put(Category.PLATFORM, platform);
        DokitConstant.KIT_MAPS.put(Category.TOOLS, tool);
        DokitConstant.KIT_MAPS.put(Category.UI, ui);
        DokitConstant.KIT_MAPS.put(Category.FLOAT_MODE, floatMode);
        DokitConstant.KIT_MAPS.put(Category.CLOSE, exit);
        DokitConstant.KIT_MAPS.put(Category.VERSION, version);
        //初始化悬浮窗管理类
        DokitViewManager.getInstance().init(app);
        initAndroidUtil(app);
    }

    private static void initAndroidUtil(Application app) {
        Utils.init(app);
        LogUtils.getConfig()
                // 设置 log 总开关，包括输出到控制台和文件，默认开
                .setLogSwitch(true)
                // 设置是否输出到控制台开关，默认开
                .setConsoleSwitch(true)
                // 设置 log 全局标签，默认为空，当全局标签不为空时，我们输出的 log 全部为该 tag， 为空时，如果传入的 tag 为空那就显示类名，否则显示 tag
                .setGlobalTag("Doraemon")
                // 设置 log 头信息开关，默认为开
                .setLogHeadSwitch(true)
                // 打印 log 时是否存到文件的开关，默认关
                .setLog2FileSwitch(true)
                // 当自定义路径为空时，写入应用的/cache/log/目录中
                .setDir("")
                // 当文件前缀为空时，默认为"util"，即写入文件为"util-MM-dd.txt"
                .setFilePrefix("djx-table-log")
                // 输出日志是否带边框开关，默认开
                .setBorderSwitch(true)
                // 一条日志仅输出一条，默认开，为美化 AS 3.1 的 Logcat
                .setSingleTagSwitch(true)
                // log 的控制台过滤器，和 logcat 过滤器同理，默认 Verbose
                .setConsoleFilter(LogUtils.V)
                // log 文件过滤器，和 logcat 过滤器同理，默认 Verbose
                .setFileFilter(LogUtils.E)
                // log 栈深度，默认为 1
                .setStackDeep(2)
                // 设置栈偏移，比如二次封装的话就需要设置，默认为 0
                .setStackOffset(0);
    }


    private static void requestPermission(Context context) {
        if (!PermissionUtil.canDrawOverlays(context) && !sHasRequestPermission) {
            Toast.makeText(context, context.getText(R.string.dk_float_permission_toast), Toast.LENGTH_SHORT).show();
            //请求悬浮窗权限
            PermissionUtil.requestDrawOverlays(context);
            sHasRequestPermission = true;
        }
    }

    /**
     * 显示系统悬浮窗icon
     */
    private static void showSystemMainIcon() {
        if (ActivityUtils.getTopActivity() instanceof UniversalActivity) {
            return;
        }

        if (!DokitConstant.AWAYS_SHOW_MAIN_ICON) {
            return;
        }

        DokitIntent intent = new DokitIntent(FloatIconDokitView.class);
        intent.mode = DokitIntent.MODE_SINGLE_INSTANCE;
        DokitViewManager.getInstance().attach(intent);
        DokitConstant.MAIN_ICON_HAS_SHOW = true;
    }

    /**
     * 显示所有应该显示的dokitView
     *
     * @param activity
     */
    private static void resumeAndAttachDokitViews(Activity activity) {

        DokitViewManager.getInstance().resumeAndAttachDokitViews(activity);
    }

    private static void systemDokitViewOnResume(Activity activity) {
        Map<String, AbsDokitView> dokitViewMap = DokitViewManager.getInstance().getDokitViews(activity);
        for (AbsDokitView absDokitView : dokitViewMap.values()) {
            absDokitView.onResume();
        }
    }

    /**
     * 是否忽略在当前的activity上显示浮标
     *
     * @param activity
     * @return
     */
    private static boolean ignoreCurrentActivityDokitView(Activity activity) {
        String[] ignoreActivityClassNames = new String[]{"DisplayLeakActivity"};
        for (String activityClassName : ignoreActivityClassNames) {
            if (activity.getClass().getSimpleName().equals(activityClassName)) {
                return true;
            }
        }
        return false;
    }


    static void show() {
        DokitConstant.AWAYS_SHOW_MAIN_ICON = true;
        if (!isShow()) {
            showSystemMainIcon();
        }

    }


    /**
     * 直接显示工具面板页面
     */
    static void showToolPanel() {
        DokitIntent dokitViewIntent = new DokitIntent(ToolPanelDokitView.class);
        dokitViewIntent.mode = DokitIntent.MODE_SINGLE_INSTANCE;
        DokitViewManager.getInstance().attach(dokitViewIntent);
    }


    static void hide() {
        DokitConstant.MAIN_ICON_HAS_SHOW = false;
        DokitConstant.AWAYS_SHOW_MAIN_ICON = false;
        DokitViewManager.getInstance().detach(FloatIconDokitView.class.getSimpleName());

    }

    /**
     * 禁用app信息上传开关，该上传信息只为做DoKit接入量的统计
     */
    static void disableUpload() {
        sEnableUpload = false;
    }

    static boolean isShow() {
        return DokitConstant.MAIN_ICON_HAS_SHOW;
    }


}
