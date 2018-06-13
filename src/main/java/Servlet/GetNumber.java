package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * ����ҳ������ѡָ��Ҫ��ע�ļ�,�����Զ��ύ����servlet
 * ������sessionֵ �ֱ�Ϊcorrect��correct_number
 * �ֱ��Ǵ����ѡ�ļ�������ѡ�ļ���ҳ��
 * @author sheng
 *
 */
public class GetNumber extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String radio = request.getParameter("radio");
		request.getSession().setAttribute("correct", radio);
		request.getSession().setAttribute("correct_number", Dao.CRUD.GetNumber(radio));
		request.getServletContext().getRequestDispatcher("/correct.jsp").forward(request, response);
	}

}
