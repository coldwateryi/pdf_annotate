package Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ������
 * ���ڷ�ֹδѡ����ע�ļ���ǿ�ƽ�����ע��ҳ
 * 
 * @author sheng
 *
 */
public class ValidateFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("----��������ʼ��----");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request1 = (HttpServletRequest) request;
		HttpServletResponse response1 = (HttpServletResponse) response;
		// HttpSession session = request1.getSession();
		String a = (String) request1.getSession().getAttribute("correct");
		if (a == null) {
			response1.sendRedirect("index.jsp");
		}
		chain.doFilter(request, response);
	}

	public void destroy() {
		System.out.println("----����������----");
	}
}
