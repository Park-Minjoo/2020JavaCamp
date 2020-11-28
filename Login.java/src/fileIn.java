import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.RescaleOp;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.HashMap;

public class fileIn extends JFrame {
	JMenu menu;
	JMenu menu1;
	JMenu backM;
	JMenuBar menuBar;
	JMenuItem ExitItem;
	JMenuItem FileIn;
	JMenuItem FileOut;
	JMenuItem Red;
	JMenuItem Green;
	JMenuItem Blue;
	JMenuItem xBlack;
	JMenuItem xWhite;
	JLabel label;
	JLabel label_1;
	boolean addImg = false;
	
	static BufferedImage bImg = null;
	BufferedImage cutP = null;
	static String path;

	Point location;
	MouseEvent pressed;

	private JButton brightB;
	private JSlider slider;
	private JButton mergeB;
	static int redGreen = new Color(170, 170, 85).getRGB();
	static int redBlue = new Color(170, 85, 170).getRGB();
	static int greenBlue = new Color(85, 170, 170).getRGB();
	static int black = new Color(0, 0, 0).getRGB();
	static int white = new Color(255, 255, 255).getRGB();
	private JButton greyB;
	HashMap<MyPoint, Color> MyPoint = new HashMap<MyPoint, Color>();
	private JButton edgeB;

	public fileIn() {
		getContentPane().setLayout(null);
		CreateMenu();
		setTitle("MiNjOo's ImAgE PRoceSsinG wOrLd");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 0, 1200, 800);
		label = new JLabel();
		label.setBounds(10, 10, 590, 730);
		getContentPane().add(label);
		
		label_1 = new JLabel();
		label_1.setBounds(600, 10, 590, 730);
		getContentPane().add(label_1);

		setVisible(true);
	}

	private void CreateMenu() {
		menuBar = new JMenuBar();

		menu1 = new JMenu("EXIT");
		menu = new JMenu("File");
		backM = new JMenu("Background");

		menuBar.add(menu1);
		menuBar.add(menu);
		menuBar.add(backM);

		ExitItem = new JMenuItem("종료"); // '종료'라는 메뉴아이템 생성
		FileIn = new JMenuItem("In");
		FileOut = new JMenuItem("Out");

		Red = new JMenuItem("Red");
		Green = new JMenuItem("Green");
		Blue = new JMenuItem("Blue");
		xBlack = new JMenuItem("xBlack");
		xWhite = new JMenuItem("xWhite");

		menu1.add(ExitItem); // 'EXIT' 메뉴 안에 '종료'라는 메뉴아이템 추가
		menu.add(FileIn);
		menu.add(FileOut);

		backM.add(Red);
		backM.add(Green);
		backM.add(Blue);
		backM.add(xBlack);
		backM.add(xWhite);
		
		menuBar.setBorder(BorderFactory.createLineBorder(Color.gray)); // 메뉴바 색상 지정
		TestListenr listener = new TestListenr(); // 아래의 컴포넌트 리스너 클래스 생성

		ExitItem.addActionListener(listener); // '종료' 메뉴 아이템 선택시 발생하는 이벤트 지정
		FileIn.addActionListener(listener);
		FileOut.addActionListener(listener);

		Red.addActionListener(listener);
		Green.addActionListener(listener);
		Blue.addActionListener(listener);
		xBlack.addActionListener(listener);
		xWhite.addActionListener(listener);

		setJMenuBar(menuBar);
		
		greyB = new JButton("GreyScale");
		greyB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					greyScale(bImg);
//					System.out.println("hello");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		edgeB = new JButton("Edge");
		edgeB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filter(bImg);
			}
		});
		menuBar.add(edgeB);
		menuBar.add(greyB);
		
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				e.getPoint();
//				System.out.printf("%d %d\n", e.getX(), e.getY());
				addImg = true;
				bImg = Merge(bImg, cutP, new Point(e.getPoint()));
				
			}
		});
		
		mergeB = new JButton("Merge");
		mergeB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(addImg) {
				bImg = Merge(bImg, cutP, new Point(label.getWidth(),0));
				} addImg = false;
			}
		});
		menuBar.add(mergeB);

		brightB = new JButton("Brightness");
		brightB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		menuBar.add(brightB);

		slider = new JSlider();
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(10);
		slider.setValue(100);
		slider.setMaximum(200);
		menuBar.add(slider);

		TestListenr myListener = new TestListenr();
		slider.addChangeListener(myListener);
	}

	class TestListenr implements ActionListener, ChangeListener {

		public void actionPerformed(ActionEvent event) {

			if (event.getSource() == ExitItem) { // 이벤트가 ExitItem이라면
				System.exit(1); // '종료' 선택시 프로그램 종료
			} else if (event.getSource() == FileIn) {
				try {
					fileInIn();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (event.getSource() == FileOut) {
				try {
					fileOutOut(cutP); // 마지막으로 수정한 값을 저장..
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (event.getSource() == Red) {
				BufferedImage temp;
				temp = deepCopy(bImg);
				System.out.println("Menu Red");
				getRed(temp);
			} else if (event.getSource() == Green) {
				BufferedImage temp;
				temp = deepCopy(bImg);
				System.out.println("Menu Green");
				getGreen(temp);
			} else if (event.getSource() == Blue) {
				BufferedImage temp;
				temp = deepCopy(bImg);
				System.out.println("Menu Blue");
				getBlue(temp);
			} else if (event.getSource() == xBlack) {
				cutP = deepCopy(bImg);
				System.out.println("Menu xBlack");
				getRidofBlack(cutP);
			} else if (event.getSource() == xWhite) {
				cutP = deepCopy(bImg);
				System.out.println("Menu xWhite");
				getRidofWhite(cutP);
			}
		}

		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			BufferedImage temp;
			temp = deepCopy(bImg);
			int sliderV = slider.getValue();
			if (!slider.getValueIsAdjusting()) {
				Bright(temp, (float) (sliderV * 0.01));
			}
//			System.out.println(sliderV);
		}
	}

	public BufferedImage fileInIn() throws IOException {
		JFileChooser file = new JFileChooser();
		File selectedFile = null;

		file.setCurrentDirectory(new File(System.getProperty("user.home")));
		// filter the files
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png", "jpeg");
		file.addChoosableFileFilter(filter);
		int result = file.showSaveDialog(null);
		// if the user click on save in Jfilechooser
		if (result == JFileChooser.APPROVE_OPTION) {
			selectedFile = file.getSelectedFile();
			path = selectedFile.getAbsolutePath();
//			System.out.println(path);
			label.setIcon(ResizeImage(path));
			bImg = ImageIO.read(selectedFile);
//			label.setIcon(new ImageIcon(image));
			return bImg;
		}
		// if the user click on save in Jfilechooser
		else if (result == JFileChooser.CANCEL_OPTION) {
			System.out.println("No File Select");
		}

		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

		return bImg;
	}

	public static void fileOutOut(BufferedImage temp) throws IOException {

		JFileChooser file = new JFileChooser();
		File selectedFile = null;

		file.setCurrentDirectory(new File(System.getProperty("user.home")));
		// filter the files
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png", "jpeg");
		file.addChoosableFileFilter(filter);
		int result = file.showSaveDialog(null);
		// if the user click on save in Jfilechooser
		if (result == JFileChooser.APPROVE_OPTION) {
			selectedFile = file.getSelectedFile();
			path = selectedFile.getAbsolutePath();
			
			ImageIO.write(temp,"jpg", selectedFile);
			JOptionPane.showMessageDialog(null, "파일 저장 성공", "파일 저장", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}

	// Methode to resize imageIcon with the same size of a Jlabel
	public ImageIcon ResizeImage(String ImagePath) {
		ImageIcon MyImage = new ImageIcon(ImagePath);
		Image img = MyImage.getImage();
		Image newImg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newImg);
//		imgToBuf(newImg);
		return image;
	}

	public void filter(BufferedImage imageF) {
		int x = imageF.getWidth();
		int y = imageF.getHeight();

		int maxGval = 0;
		int[][] edgeColors = new int[x][y];
		int maxGradient = -1;

		for (int i = 1; i < x - 1; i++) {
			for (int j = 1; j < y - 1; j++) {

				int val00 = getGrayScale(imageF.getRGB(i - 1, j - 1));
				int val01 = getGrayScale(imageF.getRGB(i - 1, j));
				int val02 = getGrayScale(imageF.getRGB(i - 1, j + 1));

				int val10 = getGrayScale(imageF.getRGB(i, j - 1));
				int val11 = getGrayScale(imageF.getRGB(i, j));
				int val12 = getGrayScale(imageF.getRGB(i, j + 1));

				int val20 = getGrayScale(imageF.getRGB(i + 1, j - 1));
				int val21 = getGrayScale(imageF.getRGB(i + 1, j));
				int val22 = getGrayScale(imageF.getRGB(i + 1, j + 1));

				int gx = ((-1 * val00) + (0 * val01) + (1 * val02)) + ((-2 * val10) + (0 * val11) + (2 * val12))
						+ ((-1 * val20) + (0 * val21) + (1 * val22));

				int gy = ((-1 * val00) + (-2 * val01) + (-1 * val02)) + ((0 * val10) + (0 * val11) + (0 * val12))
						+ ((1 * val20) + (2 * val21) + (1 * val22));

				double gval = Math.sqrt((gx * gx) + (gy * gy));
				int g = (int) gval;

				if (maxGradient < g) {
					maxGradient = g;
				}

				edgeColors[i][j] = g;
			}
		}
		double scale = 255.0 / maxGradient;

		for (int i = 1; i < x - 1; i++) {
			for (int j = 1; j < y - 1; j++) {
				int edgeColor = edgeColors[i][j];
				edgeColor = (int) (edgeColor * scale);
				edgeColor = 0xff000000 | (edgeColor << 16) | (edgeColor << 8) | edgeColor;

				imageF.setRGB(i, j, edgeColor);
			}
		}
		Image newImageG = imageF.getScaledInstance(label_1.getWidth(), label_1.getHeight(), Image.SCALE_SMOOTH);
		label_1.setIcon(new ImageIcon(newImageG)); // 레이블에 이미지 표시
		cutP = imageF;
	}

	public static int getGrayScale(int rgb) {
		int r = (rgb >> 16) & 0xff;
		int g = (rgb >> 8) & 0xff;
		int b = (rgb) & 0xff;

		// from https://en.wikipedia.org/wiki/Grayscale, calculating luminance
		int gray = (int) (0.2126 * r + 0.7152 * g + 0.0722 * b);
		// int gray = (r + g + b) / 3;

		return gray;
	}

	public void greyScale(BufferedImage imageG) {
		// convert to greyscale

		// read image
		for (int y = 0; y < imageG.getHeight(); y++) {
			for (int x = 0; x < imageG.getWidth(); x++) {
				// Here (x,y)denotes the coordinate of image
				// for modifying the pixel value.
				int p = imageG.getRGB(x, y);
				int a = (p >> 24) & 0xff;
				int r = (p >> 16) & 0xff;
				int g = (p >> 8) & 0xff;
				int b = p & 0xff;

				// calculate average
				int avg = (r + g + b) / 3;

				// replace RGB value with avg
				p = (a << 24) | (avg << 16) | (avg << 8) | avg;

				imageG.setRGB(x, y, p);

			}
		}
		Image newImageG = imageG.getScaledInstance(label_1.getWidth(), label_1.getHeight(), Image.SCALE_SMOOTH);
		label_1.setIcon(new ImageIcon(newImageG)); // 레이블에 이미지 표시
		cutP = imageG;
	}

	public void Bright(BufferedImage img, float brightness) {

		RescaleOp rescaleOp = new RescaleOp(brightness, 0, null);
		img = rescaleOp.filter(img, img);
		Image newImg = img.getScaledInstance(label_1.getWidth(), label_1.getHeight(), Image.SCALE_SMOOTH);
		label_1.setIcon(new ImageIcon(newImg)); // 레이블에 이미지 표시
		cutP = img;
	}

	public BufferedImage Merge(BufferedImage img1, BufferedImage img2, Point p) {
//		int width = Math.max(img1.getWidth(), img2.getWidth());
//		int height = Math.max(img1.getHeight(), img2.getHeight());
		
		BufferedImage combined = new BufferedImage(img1.getWidth(), img1.getHeight(), BufferedImage.TYPE_INT_ARGB);

		for (int w = 0; w < img1.getWidth(); w++) {
			for (int h = 0; h < img1.getHeight(); h++) {
				Color c = new Color(img1.getRGB(w, h));
				if (MyPoint.containsKey(new MyPoint(w, h))) {
					img1.setRGB(w+p.x-label.getWidth(), h+p.y, MyPoint.get(new MyPoint(w, h)).getRGB());
					
				} else {
					img1.setRGB(w, h, c.getRGB());
				}
			}
		}

		Graphics g = combined.getGraphics();
		g.drawImage(img1, 0, 0, null);
		// g.drawImage(img2, 0, 0, null);

		Image newImg = combined.getScaledInstance(label_1.getWidth(), label_1.getHeight(), Image.SCALE_SMOOTH);
		label_1.setIcon(new ImageIcon(newImg)); // 레이블에 이미지 표시

		cutP = img1;
		return img1;
	}

	public static BufferedImage deepCopy(BufferedImage bi) {
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}

	public BufferedImage getRed(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				if (new Color(image.getRGB(w, h)).getGreen() > 170) {
					image.setRGB(w, h, white);
				} else if (new Color(image.getRGB(w, h)).getBlue() > 170) {
					image.setRGB(w, h, white);
				} else if (image.getRGB(w, h) == greenBlue) {
					image.setRGB(w, h, white);
				} 
			}
		}
		Image newImageG = image.getScaledInstance(label_1.getWidth(), label_1.getHeight(), Image.SCALE_SMOOTH);
		label_1.setIcon(new ImageIcon(newImageG)); // 레이블에 이미지 표시
		System.out.println("getRed");
		
		cutP = image;
		return image;
	}

	public BufferedImage getGreen(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				if (new Color(image.getRGB(w, h)).getRed() > 170) {
					image.setRGB(w, h, white);
				} else if (new Color(image.getRGB(w, h)).getBlue() > 170) {
					image.setRGB(w, h, white);
				} else if (image.getRGB(w, h) == redBlue) {
					image.setRGB(w, h, white);
				} 
			}
		}
		Image newImageG = image.getScaledInstance(label_1.getWidth(), label_1.getHeight(), Image.SCALE_SMOOTH);
		label_1.setIcon(new ImageIcon(newImageG)); // 레이블에 이미지 표시
		System.out.println("getGreen");
		
		cutP = image;
		return image;
	}

	public BufferedImage getBlue(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				if (new Color(image.getRGB(w, h)).getRed() > 170) {
					image.setRGB(w, h, white);
				} else if (new Color(image.getRGB(w, h)).getGreen() > 170) {
					image.setRGB(w, h, white);
				} else if (image.getRGB(w, h) == redGreen) {
					image.setRGB(w, h, white);
				} 
			}
		}
		Image newImageG = image.getScaledInstance(label_1.getWidth(), label_1.getHeight(), Image.SCALE_SMOOTH);
		label_1.setIcon(new ImageIcon(newImageG)); // 레이블에 이미지 표시
		System.out.println("getBlue");

		cutP = image;
		return image;
	}

	public BufferedImage getRidofBlack(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				if (new Color(image.getRGB(w, h)).getRed() < 9) {
					image.setRGB(w, h, white);
				} else if (new Color(image.getRGB(w, h)).getGreen() < 9) {
					image.setRGB(w, h, white);
				} else if (new Color(image.getRGB(w, h)).getBlue() < 9) {
					image.setRGB(w, h, white);
				} else {
					MyPoint.put(new MyPoint(w, h), new Color(image.getRGB(w, h)));
//					System.out.println("black" + new Color(image.getRGB(w, h)));
				}
			}
		}
		Image newImageG = image.getScaledInstance(label_1.getWidth(), label_1.getHeight(), Image.SCALE_SMOOTH);
		label_1.setIcon(new ImageIcon(newImageG)); // 레이블에 이미지 표시
		System.out.println("getRidOfBlack");
		
		MyPoint = parallel_move();
		cutP = image;
		return image;
	}
	
	public BufferedImage getRidofWhite(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				if (new Color(image.getRGB(w, h)).getRed() > 250) {
					image.setRGB(w, h, white);
				} else if (new Color(image.getRGB(w, h)).getGreen() > 250) {
					image.setRGB(w, h, white);
				} else if (new Color(image.getRGB(w, h)).getBlue() > 250) {
					image.setRGB(w, h, white);
				} else {
					MyPoint.put(new MyPoint(w, h), new Color(image.getRGB(w, h)));
//					System.out.println("black" + new Color(image.getRGB(w, h)));
				}
			}
		}
		Image newImageG = image.getScaledInstance(label_1.getWidth(), label_1.getHeight(), Image.SCALE_SMOOTH);
		label_1.setIcon(new ImageIcon(newImageG)); // 레이블에 이미지 표시
		System.out.println("getRidOfWhite");
		
		cutP = image;
		return image;
	}
	
	public HashMap<MyPoint, Color> parallel_move(){
		HashMap<MyPoint, Color> temp = new HashMap<MyPoint, Color>();
		int min_x = 200000;
		int min_y = 200000;
		for(MyPoint p: MyPoint.keySet()) {
			min_x = Math.min(p.x,min_x);
			min_y = Math.min(p.y,min_y);
		}
		for(MyPoint p: MyPoint.keySet()) {
			temp.put(new MyPoint(p.x - min_x, p.y - min_y), MyPoint.get(p));
		}
			
		return temp;
	}
	
	
	public static void main(String args[]) {
		fileIn test = new fileIn();
	}
}