package com.iqbon.jcms.web.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.iqbon.jcms.util.KeyConstant;

/**
 * session过滤器
 */
public class SessionFilter implements Filter {

  /**
   * 需要排除的页面
   */
  private String excludedPages;

  private String[] excludedPageArray;
	  /**
   * @see Filter#destroy()
   */
  public void destroy() {
    return;
  }

  /**
   * 对session进行判断当前访问是否有登录
   */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    boolean isExcludedPage = false;
    for (String page : excludedPageArray) {//判断是否在过滤url之外
      if(((HttpServletRequest) request).getServletPath().equals(page)){
        isExcludedPage = true;
        break;
      }
    }
    if (isExcludedPage) {//在过滤url之外
      chain.doFilter(request, response);
    } else {//不在过滤url之外，判断session是否存在
      HttpSession session = ((HttpServletRequest) request).getSession();
      if (session == null || session.getAttribute(KeyConstant.SESSION_KEY_USER) == null) {
        ((HttpServletResponse) response).sendRedirect("/login.htm");
      } else {
        chain.doFilter(request, response);
      }
    }
	}

	  /**
   * 初始化函数，获取需要排除在外的url
   */
	public void init(FilterConfig fConfig) throws ServletException {
    excludedPages = fConfig.getInitParameter("excludedPages");
    if (StringUtils.isNotEmpty(excludedPages)) {
      excludedPageArray = excludedPages.split(",");
    }
    return;
	}

}
