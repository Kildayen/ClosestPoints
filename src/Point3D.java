/**
 * This class defines a 3d point object that stores each of its coordinates as a double.
 * 
 * @author Adam Warner
 * @version 7/11/2015
 *
 */

public class Point3D 
{
	private double x;
	private double y;
	private double z;
	
	/**
	 * Constructor for the Point3d object
	 */
	public Point3D(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * This method calculates the distance to a given Point3D object.
	 * 
	 * @param newPoint			The second point
	 * @return testDistance		The distance between the two points	
	 */
	public double getDistance(Point3D newPoint)
	{
		double testX, testY, testZ;
		double testDistance;
		
		testX = x - newPoint.x;
		testY = y - newPoint.y;
		testZ = z - newPoint.z;
		
		testX = testX * testX;
		testY = testY * testY;
		testZ = testZ * testZ;
		
		testDistance = Math.sqrt(testX + testY + testZ);
		return testDistance;
	}
}
