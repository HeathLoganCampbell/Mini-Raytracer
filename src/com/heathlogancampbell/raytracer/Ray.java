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
public class Ray implements Cloneable 
{
	public static double RAY_T_MAX = Double.MAX_VALUE; // infinit distance
	public static double RAY_T_MIN = 0.0001; // stop rays bouncing off self
	public Vector3f position, velocity;
	public double tMax;

	public Ray(Vector3f position, Vector3f velocity, double tMax) {
		this.position = position;
		this.velocity = velocity;
		this.tMax = tMax;
	}
	
	public Ray(Vector3f position, Vector3f velocity) {
		this(position, velocity, RAY_T_MAX);
	}

	public Ray() {
		this(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), RAY_T_MAX);
	}

	public Vector3f calculate(double t)
	{
		return position.add(velocity.scale(t));
	}
}
