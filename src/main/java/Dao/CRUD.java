package Dao;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Domain.PdfData;
import org.apache.ibatis.session.SqlSession;

/**
 * �������ڶ����ݿ���г����CRUD
 * 
 * @author sheng
 *
 */
public class CRUD {
	/**
	 * �����ϴ�pdf�ļ��������ݿ�
	 * 
	 * @param upload_Document
	 *            pdf�ļ���
	 */
	public static void AddName(String upload_Document) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
		String statement = "Dao.PdfDataMapper.addname";
		Domain.PdfData p = new Domain.PdfData();
		p.setName_File(upload_Document);
		int retResult = sqlSession.insert(statement, p);
		sqlSession.close();
	}

	/**
	 * �����ϴ�pdf�ļ���ͼƬ�Ŀ��
	 * 
	 * @param imgwidth
	 *            pdf�ļ���ͼƬ�Ŀ��
	 */
	public static void AddImgWidth(String upload_Document, int imgwidth) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
		String statement = "Dao.PdfDataMapper.addimgwidth";
		Domain.PdfData p = new Domain.PdfData();
		p.setName_File(upload_Document);
		p.setImg_Width(imgwidth);
		int retResult = sqlSession.insert(statement, p);
		sqlSession.close();
	}

	/**
	 * �����ϴ�pdf�ļ���ͼƬ�ĸ߶�
	 * 
	 * @param imgheight
	 *            pdf�ļ���ͼƬ�ĸ߶�
	 */
	public static void AddImgHeight(String upload_Document, int imgheight) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
		String statement = "Dao.PdfDataMapper.addimgheight";
		Domain.PdfData p = new Domain.PdfData();
		p.setName_File(upload_Document);
		p.setImg_Height(imgheight);
		int retResult = sqlSession.insert(statement, p);
		sqlSession.close();
	}

	/**
	 * �����ϴ�pdfҳ�������ݿ�
	 * 
	 * @param upload_Document
	 *            pdf�ļ���
	 * @param PDF_Number
	 *            pdfת����ͼƬ���ͼƬ����
	 */
	public static void AddNumber(String upload_Document, int PDF_Number) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
		String statement = "Dao.PdfDataMapper.addnumber";
		Domain.PdfData p = new Domain.PdfData();
		p.setName_File(upload_Document);
		p.setPDF_Number(PDF_Number);
		int retResult = sqlSession.insert(statement, p);
		sqlSession.close();
	}

	/**
	 * ���ڴ����ݿ��ȡpdfҳ��,�˴���ȡ����ֵ��������ʽ���˴���,�õ�����ط���ΪgetSubUtilSimple()
	 * 
	 * @param upload_Document
	 *            pdf�ļ���
	 * @return pdfת����ͼƬ���ͼƬ����
	 */
	public static int GetNumber(String upload_Document) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		String statement = "Dao.PdfDataMapper.getnumber";
		Domain.PdfData p = new Domain.PdfData();
		p.setName_File(upload_Document);
		List<String> retResult = (List) sqlSession.selectList(statement, p);
		String str = retResult.toString();
		String rgex = "=(.*?)}";
		String a = getSubUtilSimple(str, rgex);
		int i = Integer.parseInt(a);
		sqlSession.close();
		return i;
	}

	/**
	 * ���ڻ�ȡpdf�ļ���ͼƬ�Ŀ��
	 * 
	 * @param upload_Document
	 *            pdf�ļ���
	 * @return pdf�ļ���ͼƬ�Ŀ��
	 */
	public static int GetImgWidth(String upload_Document) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		String statement = "Dao.PdfDataMapper.getimgwidth";
		Domain.PdfData p = new Domain.PdfData();
		p.setName_File(upload_Document);
		List<String> retResult = (List) sqlSession.selectList(statement, p);
		String str = retResult.toString();
		String rgex = "=(.*?)}";
		String a = getSubUtilSimple(str, rgex);
		int i = Integer.parseInt(a);
		sqlSession.close();
		return i;
	}

	/**
	 * ���ڻ�ȡpdf�ļ���ͼƬ�ĸ߶�
	 * 
	 * @param upload_Document
	 *            pdf�ļ���
	 * @return pdf�ļ���ͼƬ�ĸ߶�
	 */
	public static int GetImgHeight(String upload_Document) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		String statement = "Dao.PdfDataMapper.getimgheight";
		Domain.PdfData p = new Domain.PdfData();
		p.setName_File(upload_Document);
		List<String> retResult = (List) sqlSession.selectList(statement, p);
		String str = retResult.toString();
		String rgex = "=(.*?)}";
		String a = getSubUtilSimple(str, rgex);
		int i = Integer.parseInt(a);
		sqlSession.close();
		return i;
	}


	/**
	 * ���ڴ����ݿ��ȡ�����ļ���
	 * 
	 * @return ����list���͵��ļ���
	 */
	public static List<Object> GetName() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		String statement = "Dao.PdfDataMapper.getname";
		List<Object> user = sqlSession.selectList(statement);
		sqlSession.close();
		System.out.println(user);
		return user;
	}

	/**
	 * 
	 * @param soap
	 *            ��Ҫ���������ı�
	 * @param rgex
	 *            ������ʽ
	 * @return ���ؼ�����ϵ��ı�
	 */
	// ������ʽ
	public static String getSubUtilSimple(String soap, String rgex) {
		Pattern pattern = Pattern.compile(rgex);
		Matcher m = pattern.matcher(soap);
		while (m.find()) {
			return m.group(1);
		}
		return "";
	}

}
