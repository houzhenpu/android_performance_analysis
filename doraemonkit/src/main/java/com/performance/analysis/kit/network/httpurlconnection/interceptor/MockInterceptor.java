package com.performance.analysis.kit.network.httpurlconnection.interceptor;

import androidx.annotation.NonNull;

import com.performance.analysis.kit.network.core.ResourceTypeHelper;
import com.performance.analysis.okgo.OkGo;
import com.performance.analysis.kit.network.httpurlconnection.HttpRequest;
import com.performance.analysis.kit.network.httpurlconnection.HttpResponse;
import com.performance.analysis.kit.network.httpurlconnection.chain.HttpRequestChain;
import com.performance.analysis.kit.network.httpurlconnection.chain.HttpRequestStreamChain;
import com.performance.analysis.kit.network.httpurlconnection.chain.HttpResponseChain;
import com.performance.analysis.kit.network.httpurlconnection.chain.HttpResponseStreamChain;

import java.io.IOException;

import okhttp3.HttpUrl;

/**
 * @author jintai
 * @desc: 接口mock拦截器
 */
public class MockInterceptor implements DKInterceptor<HttpRequest, HttpResponse> {
    public static final String TAG = "MockInterceptor";


    public MockInterceptor() {
    }

    private ResourceTypeHelper mResourceTypeHelper;

    @Override
    public void intercept(@NonNull HttpRequestChain chain, @NonNull HttpRequest oldRequest) throws IOException {
        chain.process(oldRequest);
    }

    /**
     * 命中拦截
     *
     * @param chain
     * @param response
     * @throws IOException
     */
    @Override
    public void intercept(@NonNull HttpResponseChain chain, @NonNull HttpResponse response) throws IOException {
        try {
            httpProxy(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        chain.process(response);
    }

    @Override
    public void intercept(@NonNull HttpRequestStreamChain chain, @NonNull HttpRequest request) throws IOException {
        chain.process(request);
    }

    @Override
    public void intercept(@NonNull HttpResponseStreamChain chain, @NonNull HttpResponse response) throws IOException {
        try {
            httpProxy(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        chain.process(response);
    }


    /**
     * 将HttpUrlConnection请求代理成OkHttp发送
     *
     * @param httpResponse
     * @throws Exception
     */
    private void httpProxy(HttpResponse httpResponse) throws Exception {

        HttpUrl mockUrl = HttpUrl.parse(httpResponse.getUrl());
        if (mockUrl != null) {
            HttpUrl originUrl = HttpUrl.parse(mockUrl.queryParameter("originUrl"));
            if (originUrl != null) {
                //用okhttp代理发送
                OkGo.<String>get(originUrl.toString()).execute();
            }
        }

    }


}
