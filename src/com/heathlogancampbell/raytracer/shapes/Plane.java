package com.heathlogancampbell.raytracer.shapes;

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
public class Plane 
{
	private Vector3f point, normal;

	public Plane(Vector3f point, Vector3f normal) {
		this.point = point;
		this.normal = normal;
	}
	
	
}
