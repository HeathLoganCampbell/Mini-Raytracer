package com.heathlogancampbell.raytracer.utils;

public class Colour 
{
	public int r;
	public int g;
	public int b;
	
	public Colour(int r, int g, int b)
	{
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public Colour(int all)
	{
		this(all, all, all);
	}
	
	public void add(Colour colour)
	{
		this.r += colour.r;
		this.g += colour.g;
		this.b += colour.b;
	}
	
	public void multiple(Colour colour)
	{
		this.r *= colour.r;
		this.g *= colour.g;
		this.b *= colour.b;
	}
	
	public void scale(int scalar)
	{
		this.r *= scalar;
		this.g *= scalar;
		this.b *= scalar;
	}
	
	public void applyGammaCorrection(float exposure, float gamma )
	{
		this.r = (int) Math.pow(this.r * exposure, gamma);
		this.g = (int) Math.pow(this.g * exposure, gamma);
		this.b = (int) Math.pow(this.b * exposure, gamma);
	}
	
	@Override
	public String toString() {
		return new String("[" + r + ", " + g + ", " + b + "]");
	}
}
