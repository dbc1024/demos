package com.ectrip.zuul.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;  
import java.io.IOException;  
import java.io.InputStream;  
  
import org.springframework.http.HttpHeaders;  
import org.springframework.http.HttpStatus;  
import org.springframework.http.MediaType;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ectrip.zuul.entity.ResponseBean;
@Component
public class ServiceFallBackProvider implements ZuulFallbackProvider {

	@Override
	public String getRoute() {

		return null;// api服务id，如果需要所有调用都支持回退，则return "*"或return null
	}

	@Override
	public ClientHttpResponse fallbackResponse() {

		return new ClientHttpResponse() {

			@Override
			public InputStream getBody() throws IOException {
				ResponseBean result = new ResponseBean(400, "当前服务不可用，请稍后重试", null);
				return new ByteArrayInputStream(JSON.toJSONString(result).getBytes("UTF-8"));
			}

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				// 和body中的内容编码一致，否则容易乱码
				headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
				return headers;
			}

			/**
			 * 网关向api服务请求是失败了，但是消费者客户端向网关发起的请求是OK的， 不应该把api的404,500等问题抛给客户端
			 * 网关和api服务集群对于客户端来说是黑盒子
			 */
			@Override
			public HttpStatus getStatusCode() throws IOException {

				return HttpStatus.OK;
			}

			@Override
			public int getRawStatusCode() throws IOException {

				return HttpStatus.OK.value();
			}

			@Override
			public String getStatusText() throws IOException {

				return HttpStatus.OK.getReasonPhrase();
			}

			@Override
			public void close() {

			}

		};
	}
}
