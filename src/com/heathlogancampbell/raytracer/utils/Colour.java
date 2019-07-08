package com.heathlogancampbell.raytracer.utils;

public class Colour 
{
	private double r, g, b;
	
	public Colour(double r, double g, double b)
	{
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public Colour(double all)
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
	
	public void scale(double scalar)
	{
		this.r *= scalar;
		this.g *= scalar;
		this.b *= scalar;
	}
	
	public void applyGammaCorrection(float exposure, float gamma )
	{
		this.r = Math.pow(this.r * exposure, gamma);
		this.g = Math.pow(this.g * exposure, gamma);
		this.b = Math.pow(this.b * exposure, gamma);
	}
}
