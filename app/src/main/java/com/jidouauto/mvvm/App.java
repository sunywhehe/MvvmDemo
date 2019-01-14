package com.jidouauto.mvvm;

import android.support.multidex.MultiDexApplication;
import com.allen.library.RxHttpUtils;
import com.allen.library.config.OkHttpConfig;
import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;
import com.elvishew.xlog.flattener.ClassicFlattener;
import com.elvishew.xlog.printer.AndroidPrinter;
import com.elvishew.xlog.printer.Printer;
import com.elvishew.xlog.printer.file.FilePrinter;
import com.elvishew.xlog.printer.file.naming.DateFileNameGenerator;
import com.jidouauto.mvvm.constants.HttpConstants;
import com.jidouauto.mvvm.util.AppUtils;
import com.jidouauto.mvvm.util.CommonUtils;
import com.squareup.leakcanary.LeakCanary;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author leosun
 * Created by Leosun on 2019/1/7 15:34
 */
public class App extends MultiDexApplication {

    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        init();
    }

    /**
     * application 所有初始化项
     * 注意 ，初始化 顺序必须是 appContext -> logger -> rxhttp
     */
    private void init() {
        AppUtils.init(this);
        initLogger();
        initRxHttp();
    }

    /**
     * 初始化 logger , 初始化配置项，不影响效率
     */
    private void initLogger() {
        LogConfiguration config = new LogConfiguration.Builder()
                // 指定日志级别，低于该级别的日志将不会被打印，默认为 LogLevel.ALL
                .logLevel(BuildConfig.IS_SHOW_LOG ? LogLevel.ALL : LogLevel.INFO)
                // 指定 TAG，默认为 "X-LOG"
                .tag("Leosun")
                // 允许打印深度为2的调用栈信息，默认禁止
                .st(1)
                .build();

        // Printer that print the log using android.util.Log
        Printer androidPrinter = new AndroidPrinter();
        // Printer that print the log to the file system
        Printer filePrinter = new FilePrinter
                // Specify the path to save log file
                .Builder(CommonUtils.getXlogFolderPath())
                // Default: ChangelessFileNameGenerator("log")
                .fileNameGenerator(new DateFileNameGenerator())
                // Default: FileSizeBackupStrategy(1024 * 1024)
                // .backupStrategy(new MyBackupStrategy())
                // Default: NeverCleanStrategy()
//                .cleanStrategy(new FileLastModifiedCleanStrategy(MAX_TIME))
                // Default: DefaultFlattener
                .flattener(new ClassicFlattener())
                .build();
        XLog.init(config, androidPrinter, filePrinter);
    }

    private void initRxHttp() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(message -> {
            XLog.nst().d("OKHTTP:" + message);
        });
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpConfig.Builder(this)
                //全局的请求头信息
                //.setHeaders(headerMaps)
                //开启缓存策略(默认false)
                //1、在有网络的时候，先去读缓存，缓存时间到了，再去访问网络获取数据；
                //2、在没有网络的时候，去读缓存中的数据。
//                .setCache(true)
                //全局持久话cookie,保存本地每次都会携带在header中（默认false）
                //.setSaveCookie(true)
                //可以添加自己的拦截器(比如使用自己熟悉三方的缓存库等等)
                .setAddInterceptor(logInterceptor)
                //全局ssl证书认证
                //1、信任所有证书,不安全有风险（默认信任所有证书）
                //.setSslSocketFactory()
                //2、使用预埋证书，校验服务端证书（自签名证书）
                //.setSslSocketFactory(cerInputStream)
                //3、使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
                //.setSslSocketFactory(bksInputStream,"123456",cerInputStream)
                //全局超时配置
                .setReadTimeout(30)
                //全局超时配置
                .setWriteTimeout(30)
                //全局超时配置
                .setConnectTimeout(30)
                //全局是否打开请求log日志
                .setDebug(false).build();

        RxHttpUtils.getInstance().init(this).config()
                //配置全局baseUrl
                .setBaseUrl(HttpConstants.SEARCH_BASE_URL)
                //开启全局配置
                .setOkClient(okHttpClient);
    }
}
