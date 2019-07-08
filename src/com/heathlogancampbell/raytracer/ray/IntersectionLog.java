package com.heathlogancampbell.raytracer.ray;

import com.heathlogancampbell.raytracer.shapes.Shape;

public class IntersectionLog
{
	public Ray ray;
	public double t;
	public Shape hitShape;
	
	public IntersectionLog(Ray ray)
	{
		this.ray = ray;
		this.t = Ray.RAY_T_MAX;
	}
}
