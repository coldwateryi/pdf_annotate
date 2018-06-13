package Servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ��ע���ṩ���ع���
 * 
 * @author sheng
 *
 */
public class DownloadDocument extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1.��ȡҪ���ص��ļ��ľ���·��
		String realPath = this.getServletContext()
				.getRealPath("/Document/correct/pdf_canvas/" + request.getSession().getAttribute("correct") + ".pdf");
		// 2.��ȡҪ���ص��ļ���
		String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
		// 3.����content-disposition��Ӧͷ��������������ص���ʽ���ļ�
		response.setHeader("content-disposition", "attachment;filename=" + fileName);
		// 4.��ȡҪ���ص��ļ�������
		InputStream in = new FileInputStream(realPath);
		int len = 0;
		// 5.�������ݻ�����
		byte[] buffer = new byte[1024];
		// 6.ͨ��response�����ȡOutputStream��
		OutputStream out = response.getOutputStream();
		// 7.��FileInputStream��д�뵽buffer������
		while ((len = in.read(buffer)) > 0) {
			// 8.ʹ��OutputStream��������������������ͻ��������
			out.write(buffer, 0, len);
		}
		in.close();
		request.getSession().removeAttribute("");
	}

}
