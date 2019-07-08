package com.heathlogancampbell.raytracer.shapes;

import com.heathlogancampbell.raytracer.ray.IntersectionLog;
import com.heathlogancampbell.raytracer.ray.Ray;
import com.heathlogancampbell.raytracer.utils.Vector3f;

/**
 * we assume we start in the middle (0,0)
 * lets r be radius 
 * || p + dt || = r
 * ||v|| = sqrt(dot(v, v))
 *  thus
 *  sqrt(dot((p+dt), (p+dt))) = r
 *  then
 *  dot((p+dt), (p+dt)) = r^2 [Distances are always postive, r should always be postive]
 *  p * p + t^2(d * d) + 2t(p*d) = r^2
 *  
 *  t^2 ||d||^2 + 2t(p * d) + ||p||^2 - r^2 = 0
 *  this is a quadratic in t
 *  a = ||d||^2
 *  b = 2(p*d)
 *  c = ||p||^2 - r^2
 *  
 *  has 0 -> 2 solutions
 *  in this case, we'll have
 *  0 if it missed the sphere
 *  1 if it just skimmed it
 *  2 if it went throught the sphere and out the other end
 *
 */
public class Sphere extends Shape
{
	private double radius;
	private double radiusSq;
	
	public Sphere(Vector3f point, double radius)
	{
		super(point);
		this.radius = radius;
		this.radiusSq = this.radius * this.radius;
	}

	@Override
	public boolean intersection(IntersectionLog intersectionLog) 
	{
		Ray ray = intersectionLog.ray;
		Vector3f delta = ray.position.subtract(this.point);

		float a = ray.velocity.dot(ray.velocity);
		float b = (float) (2.0 * delta.dot(ray.velocity));
		float c = (float) (delta.dot(delta) - this.radiusSq);
		float discriminant = (float) (b * b - 4.0 * a * c);
		if(discriminant < 0)
		{
			return false;
		}
		
		intersectionLog.t = (-b - Math.sqrt(discriminant)) / (2.0 * a);
		intersectionLog.hitShape = this;
		return true;
	}
}
