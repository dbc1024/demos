package com.ectrip.base.util;

import java.awt.*;
import java.awt.image.*;
import java.util.Random;

public class Picture {

	private static final String CONTENT_TYPE = "text/html; charset=utf-8";

	/**
	 * 随机数生成器
	 */
	private static Random random = new Random();

	/**
	 * 系统所有的字符
	 */
	private static Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();

	/**
	 * 排除的一些字体（这些字体会显示不正常）
	 */
	private static java.util.Set excludeFonts = new java.util.HashSet();

	static {
		excludeFonts.add("Cursor");
		excludeFonts.add("Marlett");
		excludeFonts.add("Symbol");
		excludeFonts.add("Webdings");
		excludeFonts.add("Wingdings");
		excludeFonts.add("Wingdings 2");
	}

	/**
	 * 图片的宽度
	 */
	private static int imageWidth = 65;

	/**
	 * 图片的高度
	 */
	private static int imageHeight = 20;

	/**
	 * 背景颜色
	 */
	private static Color backgroundColor = Color.WHITE;

	/**
	 * 输出的字符数
	 */
	private static int characterNumber = 4;

	/**
	 * 最终附加码的字符串
	 */
	private StringBuffer keyBuf = null;

	/**
	 * 字符的颜色数组
	 * 
	 * private static Color[] colors = new Color[]{Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY,
	 * Color.GREEN, Color.MAGENTA, Color.RED};
	 */
	private static Color[] colors = new Color[] { Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED,
			Color.RED, Color.RED };

	/**
	 * 获得随机的X值（范围在5到9之间变化）
	 * 
	 * @return int
	 */
	private int randomX() {

		return random.nextInt(1) + 4;
	}

	/**
	 * 获得随机的Y值（范围在15到19之间变化）
	 * 
	 * @return int
	 */
	private int randomY() {

		return random.nextInt(1) + 18;
	}

	/**
	 * 随机产生字符的大小
	 * 
	 * @return int 随机字符大小
	 */
	private int randomSize() {

		return 18;
	}

	/**
	 * 随机产生Font实例
	 * 
	 * @return Font 随机Font实例
	 */
	private Font randomFont() {
		Font font = null;
		//
		// if (fonts != null) {
		// font = fonts[random.nextInt(fonts.length)];
		// font = font.deriveFont(1, randomSize());
		// } else {
		// font = new Font("Arial Black", Font.PLAIN, 18);
		// }
		//
		// while (excludeFonts.contains(font.getName())) {
		// // if (fonts != null) {
		// // font = fonts[random.nextInt(fonts.length)];
		// // font = font.deriveFont(1, randomSize());
		// // } else {
		font = new Font("Arial Black", Font.PLAIN, 18);
		// }
		// }
		// Debug.print(font.getName());
		return font;
	}

	/**
	 * 128 * 随机生成一个Color实例（除了背景） 129 *
	 * 
	 * @return Color 随机Color实例 130
	 */
	private Color randomColor() {
		int index = random.nextInt(colors.length);
		return colors[index];
	}

	/**
	 * 139 * 写入一个字符 140 *
	 * 
	 * @param g
	 *            Graphics 141 *
	 * @param x
	 *            int 142 *
	 * @param y
	 *            int 143
	 */
	private void drawCharacter(Graphics g, int x, int y, int random) {

		g.setColor(randomColor());
		g.setFont(randomFont());
		g.drawString(String.valueOf(random), x, y);
	}

	public int getcharacterNumber() {
		return characterNumber;
	}

	public void getcharacter(Graphics g, int offset, int random) {
		drawCharacter(g, randomX() + offset, randomY(), random);

	}

	/**
	 * 绘制背景
	 * 
	 * @param g
	 *            Graphics
	 * @param color
	 *            Color
	 * @param x
	 *            int
	 * @param y
	 *            int
	 * @param width
	 *            int
	 * @param height
	 *            int
	 */
	private void drawBackground(Graphics g, Color color, int x, int y, int width, int height) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}

	// 绘制背景接口
	public void getbackground(Graphics g, int x, int y) {

		drawBackground(g, backgroundColor, x, y, imageWidth, imageHeight);

	}

	/**
	 * 绘制干扰点，干扰线
	 * 
	 * @param g
	 *            Graphics
	 * @param width
	 *            int
	 * @param height
	 *            int
	 */
	private void drawInterrupt(Graphics g, int width, int height) {
		int numbers = random.nextInt(50) + 50; // 50到99个干扰点
		g.setColor(Color.BLACK);
		for (int i = 0; i < numbers; i++) {
			g.fillRect(random.nextInt(width), random.nextInt(height), 1, 1);
		}

		g.drawLine(0, random.nextInt(height), width, random.nextInt(height));
	}

	public void getInterrupt(Graphics g) {
		drawInterrupt(g, imageWidth, imageHeight);
	}

	public BufferedImage getBufferedImage() {
		BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

		return image;
	}

}
