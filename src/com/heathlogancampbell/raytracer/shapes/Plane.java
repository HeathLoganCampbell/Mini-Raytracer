package com.heathlogancampbell.raytracer.shapes;

import com.heathlogancampbell.raytracer.ray.IntersectionLog;
import com.heathlogancampbell.raytracer.ray.Ray;
import com.heathlogancampbell.raytracer.utils.Colour;
import com.heathlogancampbell.raytracer.utils.Vector3f;

/**
 * A plane is a infinite sheet
 * 
 * Lets say Pp is a point on the plane
 * Lets say we have a normal of the plane N (which is 90 degrees to the plane)
 *
 *	(p - Pp) will be 90 degree to the normal thus will be the dot product = 0
 *
 *  (p - Pp) * n = 0
 *
 *	Lets plug the ray equation in to get T
 *  
 *  t = 1/(d * n) (Pp * n - P * n)
 *  
 *  (Pp * n - P * n) = distance of starting point (if closer, it's smaller)
 *  1/(d * n) = how fast we get there
 */
public class Plane extends Shape
{
	private Vector3f normal;

	public Plane(Vector3f point, Vector3f normal, Colour colour) {
		super(point, colour);
		this.normal = normal;
	}
	
	@Override
	public boolean intersection(IntersectionLog intersectionLog) 
	{
		float dirDotN = intersectionLog.ray.velocity.dot(normal);
		if (dirDotN == 0.0f) return false;
		
		double t = this.point.subtract(intersectionLog.ray.position).dot(this.normal) / dirDotN;
		
		if(t <= Ray.RAY_T_MIN || t >= intersectionLog.t)
			return false;
		
		intersectionLog.t = t;
		intersectionLog.hitShape = this;
		intersectionLog.colour = colour;		
		return true;
	}

	@Override
	public Vector3f getNormal(Vector3f vector) {
		return this.normal;
	}
}
