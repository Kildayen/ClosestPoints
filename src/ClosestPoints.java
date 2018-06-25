/**
 * ClosestPoints reads coordinates from a file and calculates which two points are closest.
 * 
 * @author Adam Warner
 * @version 7/11/2015
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

import edu.rit.pj2.Loop;
import edu.rit.pj2.Task;

public class ClosestPoints extends Task
{

	/**
	 * The main method for this class. Expects a filename as an argument;
	 * 
	 * @param args		The name of the file to read
	 */
	public void main(String[] args)
	{
		File coordFile;
		LinkedList<Point3D> list = new LinkedList<Point3D>();
		DistanceVbl min = new DistanceVbl();
		int error = 0;
	
		// Check validity of command line arguments
		if (args.length != 1)
			Usage(4);
		
		coordFile = new File(args[0]);

		// Check validity of given file
		if (!coordFile.canRead())
		{
	        System.err.println(coordFile + " does not exist or is unreadable.");
	        Usage(0);
		}
		
		Scanner scanFile = null;
		try 
		{
			scanFile = new Scanner(coordFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Read file contents into lists
		while (scanFile.hasNextLine() && error == 0)
		{
			String line = scanFile.nextLine();
			StringTokenizer st = new StringTokenizer(line);
			double x = 0.0, y = 0.0, z = 0.0;
			
			Scanner scanLine = new Scanner(line);
		
			// Check for valid x value
			if (st.countTokens() == 3)
			{
				if (scanLine.hasNextDouble())
				{
					x = scanLine.nextDouble();
				} else 
					error = 1;
			
				// Check for valid y value
				if (scanLine.hasNextDouble())
				{
					y = scanLine.nextDouble();
				} else 
					error = 1;
				
				// Check for valid z value
				if (scanLine.hasNextDouble())
				{
					z = scanLine.nextDouble();
				} else 
					error = 1;
			} else error = 2;

			scanLine.close();
			
			// If an error was discovered, give an error message and end the program
			if (error != 0)
			{
				scanFile.close();
				Usage(error);
			}

			list.add(new Point3D(x, y, z));
		}

		scanFile.close();
	
		// Check number of lines read 
		if (list.size() < 2)
			Usage(3);

		// Calculate closest points
		parallelFor (0, list.size() - 1) .exec (new Loop()
		{
			double testDistance;
			DistanceVbl thMin;
			Point3D point1, point2;
			
			/**
			 * Initialize each thread
			 */
		    public void start()
            {
		    	thMin = threadLocal(min); 
            }
			
			/**
			 * Test each pair of points for least distance
			 */
			public void run(int i)
			{
				for (int j = 0; j < i; j++)
				{
					if (i == j) continue;
					
					point1 = list.get(i);
					point2 = list.get(j);
					
					testDistance = point1.getDistance(point2);
					thMin.compare(j, i, testDistance);
				}
			}
		});

		// Display final result
		min.display();
	}

	/**
	 * Displays the selected error message, if any, then throws an error to end the program.
	 * 
	 * @param i		The code for the message displayed
	 */
	public void Usage(int i)
	{
		switch (i)
		{
		case 1: System.err.println("Invalid line format: Each coordinate must be a value of type double."); break;
		case 2:	System.err.println("Invalid line format: Each line must contain three values."); break;
		case 3: System.err.println("Invalid file: The input file must contain at least two lines."); break;
		case 4: System.err.println("Usage: java pj2 ClosestPoints <file>"); break;
		default: break;
		}
		throw new IllegalArgumentException();
	}
}