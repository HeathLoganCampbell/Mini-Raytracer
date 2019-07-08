package com.heathlogancampbell.raytracer.ray;

import com.heathlogancampbell.raytracer.shapes.Shape;
import com.heathlogancampbell.raytracer.utils.Colour;

public class IntersectionLog
{
	public Ray ray;
	public double t;
	public Shape hitShape;
	public Colour colour;
	
	public IntersectionLog(Ray ray)
	{
		this.ray = ray;
		this.t = Ray.RAY_T_MAX;
	}
}
