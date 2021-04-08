/**
* Course: Intro To Computer Science
* Homework: 5
* Task: 1
* Name: Guy Itzhaki
* E-mail: itzhaki1234@gmail.com
*/

public class BasicEditor 
{

	public static void main(String[] args) 
	{
		String file=args[0];
		if (!file.contains(".ppm"))
			file=file.concat(".ppm");
		if (args.length==2)
		{
			String op=args[1];
			int[][][] pic=ImageOps.read(file);
			switch (op)
			{
				case "fh": ImageOps.flipHorizontally(pic);
					break;
				case "fv": ImageOps.flipVertically(pic);
					break;
				case "gr": pic=ImageOps.greyScaled(pic);;
					break;
			}
			ImageOps.show(pic);
		}
		else
			ImageOps.show(ImageOps.read(file));
	}
}
