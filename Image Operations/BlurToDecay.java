/**
* Course: Intro To Computer Science
* Homework: 5
* Task: 2
* Name: Guy Itzhaki
* E-mail: itzhaki1234@gmail.com
*/

public class BlurToDecay
{

	public static void main(String[] args) 
	{
		String file=args[0];
		if (!file.contains(".ppm"))
			file=file.concat(".ppm");
		int n=Integer.parseInt(args[1]);
		int[][][] pic=ImageOps.read(file);
		pic=ImageOps.edges(pic);
		ImageOps.show(pic);
		for (int i=0; i<n; i++)
		{
			pic=ImageOps.blurred(pic);
			ImageOps.show(pic);
		}
	}
}
