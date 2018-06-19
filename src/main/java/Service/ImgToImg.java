package Service;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImgToImg {
	/**
	 * 
	 * @param int_maxa
	 *            pdf����עͼƬ����(��������ȵ�)
	 * @param s
	 *            pdf�ļ���
	 * @throws Exception
	 */
	public static void ITI(int int_maxa, String s) throws Exception {
		URL path = UploadTreatment.class.getResource("/../../");
		String absolute_path_r = path + "Document/correct/";
		String absolute_path = absolute_path_r.replace("file:/", "");

		for (int i = 1; i <= int_maxa; i++) {
			String sourceFilePath = absolute_path + "img/" + s + "/" + i + ".png";
			String canvasFilePath = absolute_path + "canvas/" + s + "/" + i + ".png";
			String saveFilePath = absolute_path + "img_canvas/" + s + "/" + i + ".png";
			File folder = new File(saveFilePath);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			// �������Ӳ�
			BufferedImage buffImg = watermark(new File(sourceFilePath), new File(canvasFilePath), 0, 0, 1.0f);
			// ���ˮӡͼƬ
			generateWaterFile(buffImg, saveFilePath);
		}
		Service.ImgToPdf.ITP(int_maxa, s);
	}

	/**
	 * 
	 * @param file
	 *            pdfԭͼ
	 * @param waterFile
	 *            ��עͼƬ
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage watermark(File file, File waterFile, int x, int y, float alpha) throws IOException {
		// ��ȡ��ͼ
		BufferedImage buffImg = ImageIO.read(file);
		// ��ȡ��ͼ
		BufferedImage waterImg = ImageIO.read(waterFile);
		// ����Graphics2D�������ڵ�ͼ�����ϻ�ͼ
		Graphics2D g2d = buffImg.createGraphics();
		int waterImgWidth = waterImg.getWidth();// ��ȡ��ͼ�Ŀ��
		int waterImgHeight = waterImg.getHeight();// ��ȡ��ͼ�ĸ߶�
		// ��ͼ�κ�ͼ����ʵ�ֻ�Ϻ�͸��Ч��
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
		// ����
		g2d.drawImage(waterImg, x, y, waterImgWidth, waterImgHeight, null);
		g2d.dispose();// �ͷ�ͼ��������ʹ�õ�ϵͳ��Դ
		return buffImg;
	}

	/**
	 * 
	 * @param buffImg
	 *            �ϲ����ͼƬ
	 * @param savePath
	 *            �ϲ���ͼƬ�ı���·��
	 */
	static void generateWaterFile(BufferedImage buffImg, String savePath) {
		int temp = savePath.lastIndexOf(".") + 1;
		try {
			ImageIO.write(buffImg, savePath.substring(temp), new File(savePath));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
