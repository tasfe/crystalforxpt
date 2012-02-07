package com.netblizzard.algorithm;

//:   c03:E11_Vampire.java 
//Solution   by   Dan   Forhan 
import java.applet.*;
import java.awt.*;

public class Vampire02 extends Applet {
	private int num1, num2, product;
	private int[] startDigit = new int[4];
	private int[] productDigit = new int[4];
	private int count = 0;
	private int vampCount = 0;
	private int x, y;

	public void paint(Graphics g) {
		g.drawString("Vampire   Numbers ", 10, 20);
		g.drawLine(10, 22, 150, 22);
		// Positioning for output to applet:
		int column = 10, row = 35;
		for (num1 = 10; num1 <= 99; num1++)
			for (num2 = 10; num2 <= 99; num2++) {
				product = num1 * num2;
				startDigit[0] = num1 / 10;
				startDigit[1] = num1 % 10;
				startDigit[2] = num2 / 10;
				startDigit[3] = num2 % 10;
				productDigit[0] = product / 1000;
				productDigit[1] = (product % 1000) / 100;
				productDigit[2] = product % 1000 % 100 / 10;
				productDigit[3] = product % 1000 % 100 % 10;
				count = 0;
				for (x = 0; x < 4; x++)
					for (y = 0; y < 4; y++) {
						if (productDigit[x] == startDigit[y]) {
							count++;
							productDigit[x] = -1;
							startDigit[y] = -2;
							if (count == 4) {
								vampCount++;
								int a = (int) Math.random() * 100;
								int b = (int) Math.random() * 100;
								int c = (int) Math.random() * 100;
								if (vampCount < 10) {
									g.drawString("Vampire   number       " + vampCount + "   is   " + num1 + "   *   "
											+ num2 + "   =   " + product, column, row);
									row += 20;
								} else {
									g.drawString("Vampire   number     " + vampCount + "   is   " + num1 + "   *   "
											+ num2 + "   =   " + product, column, row);
									row += 20;
								}
							}
						}
					}
			}
	}
} // /:~
