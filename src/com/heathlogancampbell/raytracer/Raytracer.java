package com.heathlogancampbell.raytracer;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.heathlogancampbell.raytracer.camera.Camera;
import com.heathlogancampbell.raytracer.ray.IntersectionLog;
import com.heathlogancampbell.raytracer.ray.Ray;
import com.heathlogancampbell.raytracer.shapes.Plane;
import com.heathlogancampbell.raytracer.shapes.Shape;
import com.heathlogancampbell.raytracer.shapes.Sphere;
import com.heathlogancampbell.raytracer.utils.Colour;
import com.heathlogancampbell.raytracer.utils.Vector3f;

public class Raytracer extends Canvas implements Runnable 
{
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 800
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
								 new Vector3f(0,1.0f,0),
								 25.0f * Math.PI / 180,
								 (4.0f * this.img.getWidth()) / this.img.getHeight() 
								);
		
		
		this.shapes.add(new Plane(new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(0.0f, 1.0f, 0.0f), new Colour(0,0,255)));
		this.shapes.add(new Sphere(new Vector3f(0.0f, 1.0f, 0.0f), 4, new Colour(255,0,0)));
		
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
		Vector3f lightSource = new Vector3f(0, 0, 50);
		for (int x = 0; x < img.getWidth(); x++)
		{
			for (int y = 0; y < img.getHeight(); y++)
			{
				Ray ray = this.camera.makeRay((2.0 * x) / img.getWidth() - 1.0, 
											  (-2.0 * y) / img.getHeight() + 1.0);
				IntersectionLog inetersection = new IntersectionLog(ray);

				boolean intersection = false;
				for(Shape shape : this.shapes)
				{
					if(shape.intersection(inetersection))
						intersection = true;
				}
				
				if(intersection)
				{
					int colour = 0;
					
					Vector3f pi = ray.position.add(inetersection.ray.velocity.scale(inetersection.t));
					Vector3f L = lightSource.subtract(pi);
					Vector3f N = inetersection.hitShape.getNormal(pi);	
					double dt = L.normalize().dot(N.normalize());
					
					colour |=  (inetersection.colour.r * (int) (dt)) << 16;
					colour |=  (inetersection.colour.g * (int) (dt))<< 8;
					colour |=  (inetersection.colour.b * (int) (dt));
					this.pixels[x + y * this.img.getWidth()] = colour;
				}
				else
					this.pixels[x + y * this.img.getWidth()] = 0x000000;
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
				g.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);
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
