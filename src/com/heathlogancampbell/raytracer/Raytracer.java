package com.heathlogancampbell.raytracer;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.heathlogancampbell.raytracer.camera.Camera;
import com.heathlogancampbell.raytracer.ray.Ray;
import com.heathlogancampbell.raytracer.utils.Vector3f;

public class Raytracer extends Canvas implements Runnable 
{
	public static int WIDTH = 600
			 		, HEIGHT = 400
			 		, SCALE = 2;
	private boolean isRunning = false;
	private BufferedImage img;
	private int[] pixels;
	
	private Camera camera;
	
	private ArrayList<Shape> shapes;
	
	public Raytracer(int width, int height)
	{
		Dimension size = new Dimension(width, height);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		
		this.img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		this.pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
	}
	
	public void init() 
	{
		this.shapes = new ArrayList<>();
		this.camera = new Camera(new Vector3f(-5.0f, 1.0f, 0.0f),
								 new Vector3f(0.0f, 1.0f, 0.0f), 
								 new Vector3f(0,0,0),
								 25.0f * Math.PI / 180,
								 this.img.getWidth() / this.img.getHeight()
								);
		
		
		
		this.createBufferStrategy(2);
	}
	
	public synchronized void start() 
	{
		if(isRunning)
			return;
		isRunning = true;
		
		new Thread(this).start();
	}
	
	public void render()
	{
		for (int x = 0; x < img.getWidth(); x++)
		{
			for (int y = 0; y < img.getHeight(); y++)
			{
				Ray ray = this.camera.makeRay((2.0 * x) / img.getWidth() - 1.0, 
											  (-2.0 * y) / img.getHeight() + 1.0);
				
				
			}
		}
	}
	
	@Override
	public void run() 
	{
		this.init();
		
		while(this.isRunning)
		{
			
			this.render();
			
			
			BufferStrategy bs = getBufferStrategy();
			if(bs != null) 
			{
				Graphics g = bs.getDrawGraphics();
				g.drawImage(img, 0, 0, img.getWidth() * SCALE, img.getHeight() * SCALE, null);
				g.dispose();
				bs.show();
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] avg) 
	{
		JFrame frame = new JFrame("Mini-Raytracer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(WIDTH, HEIGHT);
		
		Raytracer raytracer = new Raytracer(WIDTH, HEIGHT);
		
		frame.add(raytracer);
		frame.pack();
		frame.setVisible(true);
		
		raytracer.start();
	}
}
