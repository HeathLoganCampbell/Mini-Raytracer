package com.heathlogancampbell.raytracer.shapes;

import com.heathlogancampbell.raytracer.ray.IntersectionLog;
import com.heathlogancampbell.raytracer.utils.Colour;
import com.heathlogancampbell.raytracer.utils.Vector3f;

public abstract class Shape 
{
	protected Vector3f point;
	protected Colour colour;

	public Shape(Vector3f point, Colour colour) 
	{
		this.point = point;
		this.colour = colour;
	}
	
	public abstract boolean intersection(IntersectionLog intersectionLog);
}
