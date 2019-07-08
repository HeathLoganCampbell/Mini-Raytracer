package com.heathlogancampbell.raytracer;

import com.heathlogancampbell.raytracer.utils.Vector3f;

/*
 * Lets say p is a point on the ray (vec)
 * lets say S is the start (vec)
 * lets say D is the direction (vec)
 * lets say T is distance (double) [We only care about positive t's]
 * 
 * p = s + td
 */
public class Ray 
{
	public Vector3f position, velocity;

	public Ray(Vector3f position, Vector3f velocity)
	{
		this.position = position;
		this.velocity = velocity;
	}
	
	public Ray()
	{
	}
	
	
	
}
