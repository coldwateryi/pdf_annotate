package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * ��servlet������ÿ����ҳ��ˢ���Զ���ѯ���ݿ��������ļ���
 * @author sheng
 *
 */

public class SelectData extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			request.getSession().setAttribute("pdfdata",Dao.CRUD.GetName());
		
	}

}
