package Service;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;


public class PdfToImg {
	/**
	 * 
	 * @param Upload_Document
	 *   pdf�ļ���
	 * @param img
	 *   ת��ͼƬ��ı���·��
	 * @throws Exception
	 */
	// ��pdf�ļ�ת����ͼƬ������pdf��ҳ���ϴ������ݿ�
	public static void PTI(String Upload_Document, String img) throws Exception {
		File file = new File(Upload_Document);
		String[] fileName = file.list();
		for (int i = 0; i < fileName.length; i++) {
			// ��ȡ�ļ�������׺
			String name = fileName[i].substring(0, fileName[i].lastIndexOf("."));
			String img_unmodified = img + name + "\\";
			// ���ļ��������򴴽����ļ���
			File files = new File(img_unmodified);
			if (!files.exists()) {
				files.mkdir();
			}
			PDDocument doc = PDDocument.load(Upload_Document + fileName[i]);
			List<?> pages = doc.getDocumentCatalog().getAllPages();
			for (int a = 0; a < pages.size(); a++) {
				PDPage page = (PDPage) pages.get(a); // ��ȡ��iҳ
				BufferedImage image = page.convertToImage();
				BufferedImage srcImage = resize(image, 1105, 1550);
				// ������ļ���
				// ��1��ʼ����
				String outPath = img_unmodified + (a + 1) + ".jpg";

				try (FileOutputStream out = new FileOutputStream(outPath)) {
					ImageIO.write(srcImage, "jpg", out);
				}
			}
			
			// ��pdfҳ���������ݿ�
			String pdf_name = fileName[i].substring(0, fileName[i].lastIndexOf("."));
			Dao.CRUD.AddNumber(pdf_name, pages.size());
			System.out.println(pages.size());
		}

	}
	public static BufferedImage resize(BufferedImage source, int targetW,  int targetH) throws Exception {
	       int type=source.getType();  
	       BufferedImage target=null;  
	       double sx=(double)targetW/source.getWidth();  
	       double sy=(double)targetH/source.getHeight();  
	       if(sx>sy){  
	           sx=sy;  
	           targetW=(int)(sx*source.getWidth());  
	       }else{  
	           sy=sx;  
	           targetH=(int)(sy*source.getHeight());  
	       }  
	       if(type==BufferedImage.TYPE_CUSTOM){  
	           ColorModel cm=source.getColorModel();  
	                WritableRaster raster=cm.createCompatibleWritableRaster(targetW, targetH);  
	                boolean alphaPremultiplied=cm.isAlphaPremultiplied();  
	                target=new BufferedImage(cm,raster,alphaPremultiplied,null);  
	       }else{  
	           target=new BufferedImage(targetW, targetH,type);  
	       }  
	       Graphics2D g=target.createGraphics();  
	       g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);  
	       g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));  
	       g.dispose();  
	       return target;    
	   }  
}
