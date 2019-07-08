package com.heathlogancampbell.raytracer.shapes;

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
public class Sphere 
{
	private Vector3f point;
	private double radius;
	
	public Sphere(Vector3f point, double radius)
	{
		this.point = point;
		this.radius = radius;
	}
}
