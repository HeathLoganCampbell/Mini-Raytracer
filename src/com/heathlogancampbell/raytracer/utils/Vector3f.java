package com.heathlogancampbell.raytracer.utils;

public class Vector3f
{
	public float x, y, z;
	
	public Vector3f(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3f(Vector3f vector)
	{
		this.x = vector.x;
		this.y = vector.y;
		this.z = vector.z;
	}
	
	public void scale(float scalar)
	{
		this.x *= scalar;
		this.y *= scalar;
		this.z *= scalar;
	}
	
	
	public float dot(Vector3f vector)
	{
		return (vector.x * this.x) +(vector.y * this.y) + (vector.z * this.z);
	}
	
	public Vector3f cross(Vector3f vector)
	{
		return new Vector3f(this.y * vector.z - this.z * vector.y, 
							this.z * vector.x - this.x * vector.z,
							this.x * vector.y - this.y * vector.x);
	}
	
	public float quickLength()
	{
		float t = this.x * this.x + this.y * this.y + this.z * this.z;
		return t;
	}
	
	public float length()
	{
		float t = this.x * this.x + this.y * this.y + this.z * this.z;
		return (float) Math.sqrt(t);
	}
	
	public void normalize()
	{
		float t = this.quickLength();
		if( t != 1 &&  t != 0) t = (float) (t / Math.sqrt(t));
		this.scale(t);
	}
}
