package Servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ImageTest extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ImageTest() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
				int width=300,height=100;
				//����ͼƬ����
				BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
				//ͨ��ͼƬ�����ȡGraphics ��ͼ����
				Graphics gra=image.getGraphics();
				//��ʼ��ͼ
				//���ñ���ɫ
				gra.setColor(Color.black);
				//�����������ɵ�λ�úʹ�С
				gra.fillRect(0,0,width-50,height-50);
				gra.setColor(Color.white);
				//��������
				gra.setFont(new Font("����",Font.PLAIN,18));
				//��string���ݻ���ͼ����
				gra.drawString("������֤��", 5, 20);
				gra.setColor(Color.BLUE);
				Random random =new Random();
				gra.drawLine(0, 0, 200, 70);
				//�ͷ���Դ
				gra.dispose();
				//��ȡ�����
				OutputStream output=response.getOutputStream();
				//��ͼƬ���
				ImageIO.write(image,"JPEG", output);
				output.close();
				//��ʼ��ͼ	
				//�ͷ���Դ
		 */
				Random random=new Random();
				//�����ĸ���������Ӽ� �˳�
					char[] operate={'+','-','*'};
				//������һ�����10��������
					int numsA=1+random.nextInt(9);
				//����������������4����
					int oper=random.nextInt(3);
				//�����ڶ������10��������
					int numsB=1+random.nextInt(9);	
				//��������Ϊ�����ڶ���������Ϊ��
				//���������� ��Ҫ��֤�û��ύ����֤���Ƿ���ȷ
					int result=0;
					switch(oper){
					case 0:
						result=numsA+numsB;
						break;
					case 1:
						result=numsA-numsB;
						break;
					case 2:
						result=numsA*numsB;
						break;
					}
					request.getSession().setAttribute("result", result);
					int width=70,height=40;
				//����ͼƬ����
					BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
				//ȡ��Graphics������ͼ����
					Graphics gra=image.getGraphics();
					gra.setColor(this.getRandColor(200, 250));
					gra.fillRect(0, 0, width,height);
				//���Ƹ�����
					gra.setColor(this.getRandColor(130, 160));
					for(int i=0;i<100;i++)
					{
						int x=random.nextInt(width);
						int y=random.nextInt(height);
						int x1=random.nextInt(12);
						int y1=random.nextInt(12);
						gra.drawLine(x,y,x+x1,y+y1);
					}
					gra.setColor(this.getRandColor(20, 110));
					gra.setFont(new Font("Times New Roman",Font.PLAIN,20));
					gra.drawString(numsA+" "+operate[oper]+" "+numsB+" =",5,30);
					gra.dispose();
				//��ʼ����
					OutputStream out=response.getOutputStream();
					ImageIO.write(image, "JPEG", out);
					out.close();
	}
	public Color getRandColor(int from,int end){
		Random random=new Random();
		if(from>255)from=255;
		if(end>255)end=255;
		int red = from+random.nextInt(end-from);
		int green = from+random.nextInt(end-from);
		int blue= from+random.nextInt(end-from);
		
		return new Color(red,green,blue);
	}

}
