package com.heathlogancampbell.raytracer.camera;

import com.heathlogancampbell.raytracer.ray.Ray;
import com.heathlogancampbell.raytracer.utils.Vector3f;

/**
 * 
 * Zero is the top left
 * while zero is in the middle of the camera
 * 
 * FOV = 
 * FOV is from the center to the top
 * real height is really 2 * height
 * small fov = really zoomed in
 * big fov = zoomed out
 * 
 * ASPECT RATIO = width / height
 *
 */
public class Camera 
{
	public Vector3f position
				  , up, forward, right;
	public double height, width;
	
	public Camera(Vector3f position, Vector3f target, Vector3f guidUp, double fov, double aspecRatio)
	{
		this.position = position;
		
		this.forward = (target.subtract(position)).normalize();
		this.right = forward.cross(guidUp).normalize();
		this.up = right.cross(forward);//Will have length 1 because forward and right has length 1
		
		this.height = Math.tan(fov);
		this.width = height * aspecRatio;
	}
	
	public Ray makeRay(double u, double v)
	{
		Vector3f direction = this.forward.add(this.right.scale(width * u).add(this.up.scale(height * v)));
		return new Ray(this.position, direction.normalize());
	}
}
