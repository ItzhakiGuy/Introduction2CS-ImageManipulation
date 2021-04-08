/**
* Course: Intro To Computer Science
* Homework: 5
* Task: 2
* Name: Guy Itzhaki
* E-mail: itzhaki1234@gmail.com
*/

public class BlurringEditor 
{
	
	public static void main(String[] args) 
	{
		String file=args[0];
		if (!file.contains(".ppm"))
			file=file.concat(".ppm");
		int n=Integer.parseInt(args[1]);
		int[][][] pic=ImageOps.read(file);
		int[][][] blurredPic=ImageOps.blurred(pic);
		for (int i=1; i<n; i++)
			blurredPic=ImageOps.blurred(blurredPic);
		ImageOps.show(blurredPic);		
	}
}
