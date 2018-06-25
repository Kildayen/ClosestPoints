/**
 * DistanceVbl is an implementation of the Vbl class that tracks the shortest distance between two coordinates,
 * as well as the indices associated with each coordinate.
 * 
 * @author Adam Warner
 * @version 7/3/2015
 */

import edu.rit.pj2.Vbl;

public class DistanceVbl implements Vbl 
{
	private int lo, hi;
	private double distance;
	
	/**
	 * Blank constructor
	 */
	public DistanceVbl(){}
	
	/**
	 * Copy constructor
	 * 
	 * @param distanceVbl
	 */
	public DistanceVbl(DistanceVbl dist) 
	{
		this.lo = dist.lo;
		this.hi = dist.hi;
		this.distance = dist.distance;
	}
	
	/**
	 * Creates a copy of the DistanceVbl object and returns it.
	 * 
	 * @return		the DistanceVbl object copy
	 */
	public Object clone()
	{
		return new DistanceVbl(this);
	}
	
	/**
	 * Override of Vbl reduce method. Keeps the indices with the lowest distance.
	 * 
	 * @param vbl		the DistanceVbl object to reduce
	 */
	public void reduce(Vbl vbl) 
	{
		DistanceVbl dist = (DistanceVbl) vbl;
		
		if (dist.lo != dist.hi)
		{
			if (dist.distance < this.distance || lo == hi)
			{
				if (dist.lo < dist.hi)
				{
					this.lo = dist.lo;
					this.hi = dist.hi;
				}
				else
				{
					this.hi = dist.lo;
					this.lo = dist.hi;
				}
				
				this.distance = dist.distance;
			}
		}
	}

	/**
	 * Override of Vbl set method
	 * 
	 * @param vbl	the DistanceVbl object to set
	 */
	public void set(Vbl vbl) 
	{
		DistanceVbl dist = (DistanceVbl) vbl;
		
		this.lo = dist.lo;
		this.hi = dist.hi;
		this.distance = dist.distance;
	}
	
	/**
	 * Compares current distance with a supplied distance. If the new distance is lower, save it and the indices.
	 * 
	 * @param l		the low index
	 * @param h		the high index
	 * @param dist	the distance between the two coordinates
	 */
	public void compare(int l, int h, double dist)
	{		
		if (distance == 0.0 || dist < distance)
		{
			lo = l;
			hi = h;
			distance = dist;
		}
	}
	
	/**
	 * Display contents of the DistanceVbl object
	 */
	public void display()
	{
		// Show six digits after decimal point
		String out = String.format("%.6f", distance);
		
		System.out.println(String.format(lo + "\n" + hi + "\n" + out));
	}
}