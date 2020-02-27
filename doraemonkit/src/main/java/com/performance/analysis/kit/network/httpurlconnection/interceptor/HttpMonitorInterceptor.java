package com.performance.analysis.kit.network.httpurlconnection.interceptor;

import androidx.annotation.NonNull;
import android.util.Pair;

import com.performance.analysis.kit.network.NetworkManager;
import com.performance.analysis.kit.network.bean.NetworkRecord;
import com.performance.analysis.kit.network.core.DefaultResponseHandler;
import com.performance.analysis.kit.network.core.NetworkInterpreter;
import com.performance.analysis.kit.network.httpurlconnection.inspector.URLConnectionInspectorRequest;
import com.performance.analysis.kit.network.httpurlconnection.inspector.URLConnectionInspectorResponse;
import com.performance.analysis.kit.network.utils.StreamUtil;
import com.performance.analysis.kit.network.httpurlconnection.HttpResponse;
import com.performance.analysis.kit.network.httpurlconnection.HttpRequest;
import com.performance.analysis.kit.network.httpurlconnection.chain.HttpRequestChain;
import com.performance.analysis.kit.network.httpurlconnection.chain.HttpRequestStreamChain;
import com.performance.analysis.kit.network.httpurlconnection.chain.HttpResponseChain;
import com.performance.analysis.kit.network.httpurlconnection.chain.HttpResponseStreamChain;
import com.performance.analysis.kit.network.stream.HttpOutputStreamProxy;
import com.performance.analysis.kit.network.stream.OutputStreamProxy;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * @desc: 监听HttpUrlConnection数据的拦截器
 */
public class HttpMonitorInterceptor implements DKInterceptor<HttpRequest, HttpResponse> {
    private final NetworkInterpreter mInterpreter;
    private final int mId;

    public HttpMonitorInterceptor() {
        mInterpreter = NetworkInterpreter.get();
        mId = NetworkInterpreter.get().nextRequestId();
    }

    @Override
    public void intercept(@NonNull HttpRequestChain chain, @NonNull HttpRequest request) throws IOException {
        int requestId = mId;
        if (NetworkManager.get().getRecord(requestId) != null) {
            chain.process(request);
            return;
        }
        ArrayList<Pair<String, String>> header = StreamUtil.convertHeaders(request.getHeaders());
        URLConnectionInspectorRequest inspectorRequest = new URLConnectionInspectorRequest(
                requestId,
                header,
                request.getUrl(),
                request.getMethod());
        mInterpreter.createRecord(requestId, inspectorRequest);
        chain.process(request);
    }

    @Override
    public void intercept(@NonNull HttpResponseChain chain, @NonNull HttpResponse response) throws IOException {
        int id = mId;
        NetworkRecord record = NetworkManager.get().getRecord(id);
        if (record == null) {
            chain.process(response);
            return;
        }
        ArrayList<Pair<String, String>> header = StreamUtil.convertHeaders(response.getHeaders());

        URLConnectionInspectorResponse urlConnectionInspectorResponse = new URLConnectionInspectorResponse(
                mId,
                header,
                response.getUrl(),
                response.getStatusCode());
        mInterpreter.fetchResponseInfo(record, urlConnectionInspectorResponse);
        chain.process(response);
    }

    @Override
    public void intercept(@NonNull HttpRequestStreamChain chain, @NonNull HttpRequest request) throws IOException {
        int id = mId;
        NetworkRecord record = NetworkManager.get().getRecord(id);
        if (record == null) {
            chain.process(request);
            return;
        }
        OutputStreamProxy outputStream = new HttpOutputStreamProxy(request.getOutputStream(), mId, mInterpreter);
        request.setOutputStream(outputStream);
        chain.process(request);
    }

    @Override
    public void intercept(@NonNull HttpResponseStreamChain chain, @NonNull HttpResponse response) throws IOException {
        int id = mId;
        NetworkRecord record = NetworkManager.get().getRecord(id);
        if (record == null) {
            chain.process(response);
            return;
        }
        InputStream responseStream = mInterpreter.interpretResponseStream(
                response.getHeaderField("Content-Type"),
                response.getInputStream(),
                new DefaultResponseHandler(mInterpreter, id, record));
        response.setInputStream(responseStream);
        chain.process(response);
    }
}
