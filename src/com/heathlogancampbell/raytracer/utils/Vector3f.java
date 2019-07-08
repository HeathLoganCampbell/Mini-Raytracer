package com.heathlogancampbell.raytracer.utils;


public class Vector3f implements Cloneable
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
	
	public Vector3f scale(float scalar)
	{
		return new Vector3f(this.x * scalar, this.y * scalar, this.z * scalar);
	}
	
	public Vector3f scale(double scalar) {
		return this.scale((float) scalar);
	}
	
	public Vector3f add(Vector3f vector)
	{
		return new Vector3f(this.x + vector.x, this.y + vector.y, this.z + vector.z);
	}
	
	public Vector3f subtract(Vector3f vector)
	{
		return new Vector3f(this.x - vector.x, this.y - vector.y, this.z - vector.z);
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
	
	public Vector3f normalize()
	{
		float t = this.quickLength();
		if( t != 1 &&  t != 0) t = (float) (t / Math.sqrt(t));
		Vector3f newVec = (Vector3f) this.clone();
		newVec.scale(t);
		return newVec;
	}
	
	@Override
    public Vector3f clone()
	{
        try {
            return (Vector3f) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error(e);
        }
    }
}
