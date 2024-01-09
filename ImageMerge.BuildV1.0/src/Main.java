import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		
		//Colors
		String colorReset = "\u001B[0m";
		String colorRed = "\u001B[31m";
		String colorGreen = "\u001B[32m";
		String colorYellow = "\u001B[33m";
		
		
		
		System.out.println(colorYellow + "ImageMerge by Aaditya Gupta\nImagerMerge.build.V1.0" + colorReset);
		System.out.println();
		System.out.println(colorRed + "Enter path to Backgrounds folder : " + colorReset);
		String path_BG = sc.nextLine();
		System.out.println(colorRed + "Enter path to foregrounds folder : " + colorReset);
		String path_FG = sc.nextLine();
		System.out.println(colorRed + "Enter path to output folder : " + colorReset);
		String outPath = sc.nextLine();
		
		
		
		
		
		
		File folder_BG = new File(path_BG);
		File folder_FG = new File(path_FG);
		
		File[] array_BG = folder_BG.listFiles();
		File[] array_FG = folder_FG.listFiles();
		
		for(int i = 0; i < array_BG.length; i++) {
			System.out.println(colorRed + "processing print : " + array_BG[i].getName() + colorGreen);
			for(int j = 0; j < array_FG.length; j++) {
				int folder = i+1;
				String time = String.valueOf(System.currentTimeMillis());
				String fileName = outPath + "\\" + folder + "\\" + array_BG[i].getName() + " _ " + array_FG[j].getName() + "" + time;
				try {
					mergeImage(array_BG[i].getAbsolutePath(), array_FG[j].getAbsolutePath(), fileName);
					printProgressBar();
					System.out.println(" File Created Successfully!");
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				
			}
			System.out.println(colorReset);
		}
		sc.close();
		
	}
	
	static void printProgressBar() {
		int totalTasks = 100;
        int currentTask = 0;
        int progressBarWidth = 50;

        StringBuilder progressStringBuilder = new StringBuilder();
        for (int i = 0; i < progressBarWidth; i++) {
            progressStringBuilder.append(" ");
        }

        while (currentTask <= totalTasks) {
            int progress = (int) (currentTask / (float) totalTasks * progressBarWidth);
            StringBuffer progressBarBuffer = new StringBuffer(progressBarWidth + 10);
            progressBarBuffer.append('[');
            for (int i = 0; i < progressBarWidth; i++) {
                if (i < progress) {
                    progressBarBuffer.append('=');
                } else if (i == progress) {
                    progressBarBuffer.append('>');
                } else {
                    progressBarBuffer.append(' ');
                }
            }
            progressBarBuffer.append("] ").append(progress * 100 / progressBarWidth).append('%');
            String progressBarWithBuffer = progressBarBuffer.toString();

            System.out.print("\r" + progressBarWithBuffer);

            try {
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }
            currentTask++;
        }
	}


	
	static void mergeImage(String img_path_bg, String img_path_fg, String name) {
		try {
			
			BufferedImage image_BG = ImageIO.read(new File(img_path_bg));
			BufferedImage image_FG = ImageIO.read(new File(img_path_fg));
			
			int width_BG = image_BG.getWidth();
			int height_BG = image_BG.getHeight();
			
			BufferedImage outputIMG = new BufferedImage(width_BG, height_BG, BufferedImage.TYPE_INT_RGB);
			Graphics2D render = outputIMG.createGraphics();
			render.drawImage(image_BG, 0, 0, null);
			render.drawImage(image_FG, 0, 0, null);
			
			ImageIO.write(outputIMG, "jpg", new File(name + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
