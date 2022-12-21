package com.bp.netflixservice;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;


@AllArgsConstructor
public class HeaderRequestInterceptor implements ClientHttpRequestInterceptor {
    private final String headerName;

    private final String headerValue;

    @Override
    public ClientHttpResponse intercept(HttpRequest request,
                                        byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        request.getHeaders().set(headerName, headerValue);

        return execution.execute(request, body);
    }
}
