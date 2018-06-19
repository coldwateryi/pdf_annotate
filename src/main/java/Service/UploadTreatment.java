package Service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * ���ϴ����ļ�����������
 * @author sheng
 *
 */
public class UploadTreatment {
		/**
		 * 
		 * @throws Exception
		 */
		public static void Treatment () throws Exception {
		URL path = UploadTreatment.class.getResource("/../../");
		String Upload_Document_f = path + "Upload_Document/";
		String pdf_f = path + "Document/correct/pdf/";
		String img_f = path + "Document/correct/img/";
		String Upload_Document = Upload_Document_f.replace("file:/", "");
		String pdf = pdf_f.replace("file:/", "");
		String img = img_f.replace("file:/", "");
		File[] file = (new File(Upload_Document)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				// �����ļ�
				CopyFile(file[i], new File(pdf + file[i].getName()));
			}
		}
		// ��PDF�ļ�ת��ͼƬ
		Service.PdfToImg.PTI(Upload_Document, img);
		// �����ʱ�ļ�������
		DelAllFile(Upload_Document);
	}

	// �����ļ�
	public static void CopyFile(File sourceFile, File targetFile) throws IOException {
		// �½��ļ����������������л���
		FileInputStream input = new FileInputStream(sourceFile);
		BufferedInputStream inBuff = new BufferedInputStream(input);

		// �½��ļ���������������л���
		FileOutputStream output = new FileOutputStream(targetFile);
		BufferedOutputStream outBuff = new BufferedOutputStream(output);

		// ��������
		byte[] b = new byte[1024 * 5];
		int len;
		while ((len = inBuff.read(b)) != -1) {
			outBuff.write(b, 0, len);
		}
		// ˢ�´˻���������
		outBuff.flush();

		// �ر���
		inBuff.close();
		outBuff.close();
		output.close();
		input.close();
	}
	// �����ʱ�ļ�������
	public static void DelAllFile(String Upload_Document) {
		File file = new File(Upload_Document);
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (Upload_Document.endsWith(File.separator)) {
				temp = new File(Upload_Document + tempList[i]);
			} else {
				temp = new File(Upload_Document + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				DelAllFile(Upload_Document + "/" + tempList[i]);// ɾ���ļ���������ļ�
				System.out.println("��ʱ�ļ���ɾ���ɹ�����");
			}
		}
	}
	
}
