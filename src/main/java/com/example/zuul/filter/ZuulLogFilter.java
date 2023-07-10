package com.example.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ZuulLogFilter extends ZuulFilter {

	/**
	 * 필터 사용여부 재정의
	 * @return
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		log.info("zuul pre log start");

		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();

		log.info("zuul request URI ::::: {}", request.getRequestURI());

		return null;
	}

	/**
	 * 필터 타입 재정의
	 * pre : 사전
	 * @return
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	/**
	 * 필터 정렬 재정의
	 * @return
	 */
	@Override
	public int filterOrder() {
		return 1;
	}
}
