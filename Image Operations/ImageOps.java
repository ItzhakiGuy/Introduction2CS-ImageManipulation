/**
* Course: Intro To Computer Science
* Homework: 5
* Task: 1+2
* Name: Guy Itzhaki
* E-mail: itzhaki1234@gmail.com
*/

/**
 * A library of image editing functions.
 */
public class ImageOps {
	// Use these constants, as needed.
	static final int NUM_OF_COLORS = 3;
	static final int R = 0;
	static final int G = 1;
	static final int B = 2;
	static final int MAX_COLOR_VALUE = 255;
	
	/**
	 * Reads an image in PPM format from the given filename.
	 * 
	 * @param fileName - name of the given PPM file
	 * @return - the image, as a 3-dimensional array
	 */
	public static int[][][] read(String filename) 
	{
		// Replace the following statement with your code
		StdIn.setInput(filename);
		StdIn.readLine();
		int line=StdIn.readInt();
		int row=StdIn.readInt();
		StdIn.readLine();
		int[][][] pix=new int[row][line][3];
		StdIn.readLine();
		for (int i=0; i<pix.length; i++)
		{
			for (int j=0; j<pix[i].length; j++)
				for (int t=0; t<pix[i][j].length; t++)
					pix[i][j][t]=StdIn.readInt();
			StdIn.readLine();
		}
		return pix;
	}
	
	/**
	 * Prints the array values, nicely formatted. 
	 * 
	 * @param pic - the image to display.
	 */
	public static void showData (int[][][] pic) 
	{
		for (int i=0; i<pic.length; i++)
		{
			for (int j=0; j<pic[i].length; j++)
			{
				for (int t=0; t<pic[i][j].length; t++)
					System.out.printf(pic[i][j][t]+" ");
				System.out.printf(" | ");
			}
			System.out.printf("\n");
		}
	}
	
	/**
	 * Prints the array values, nicely formatted. 
	 * 
	 * @param pic - the image to display.
	 */
	public static void showData (int[][] pic) 
	{
		for (int i=0; i<pic.length; i++)
		{
			for (int j=0; j<pic[i].length; j++)
			{
				System.out.printf(pic[i][j]+" ");
				System.out.printf(" | ");
			}
			System.out.printf("\n");
		}
	}
	
	/**
	 * Renders an image, using StdDraw. 
	 * 
	 * @param pic - the image to render.
	 */
	public static void show(int[][][] pic) 
	{
		StdDraw.setCanvasSize(pic[0].length, pic.length);
		int height = pic.length;
		int width = pic[0].length;
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
		StdDraw.show(30);
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				StdDraw.setPenColor(pic[i][j][R], pic[i][j][G], pic[i][j][B] );
				StdDraw.filledRectangle(j + 0.5, height - i - 0.5, 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
	
	/**
	 * Flips an image horizontally.
	 * SIDE EFFECT: Changes the given image.
	 * 
	 * @param pic - the image to flip
	 */
	public static void flipHorizontally(int[][][] pic) 
	{
		// Put your code here
		for (int i=0; i<pic.length; i++)
			for (int j=0; j<pic[i].length/2; j++)
				ImageOps.swap(pic, i, j, i, (pic[i].length-j-1));
	}
	
	/**
	 * Flips an image vertically
	 * * SIDE EFFECT: Changes the given image.
	 * 
	 * @param pic - the image to flip
	 */
	public static void flipVertically(int[][][] pic) 
	{
		// Put your code here
		for (int i=0; i<pic.length/2; i++)
			for (int j=0; j<pic[i].length; j++)
				ImageOps.swap(pic, i, j, (pic.length-i-1), j);
	}
	
	// Swaps the two given pixels in the given image.
	// SIDE EFFECT: Changes the pixles in the given image.
	// i1,j1 - coordinates of the first pixel
	// i2,j2 - coordinates of the second pixel
	private static void swap (int[][][] pic, int i1, int j1, int i2, int j2) 
	{
		// Put your code here
		int[] col=new int[3];
		for (int t=0; t<col.length; t++)
		{
			col[t]=pic[i1][j1][t];
			pic[i1][j1][t]=pic[i2][j2][t];
			pic[i2][j2][t]=col[t];
		}
	}
	
	/**
	 * Turns an RGB color into a greycale value, using a luminance formula.
	 * The luminance is a weighted mean of the color's value, and is given by:
	 * 0.299 * r + 0.587 * b + 0.114 * b.
	 * 
	 * @param color - the color to be greyScaled.
	 * @return the greyscale value, as an array {greyscale, greyscale, greyscale}
	 */
	public static int[] luminance(int[] color)
	{
		// Replace the following statement with your code
		int lum=(int)((0.299*color[0])+(0.587*color[1])+(0.114*color[2]));
		int[] lumi= {lum, lum, lum};
		return lumi;
	}
	
	/**
	 * Creates a greyscaled version of an image.
	 * 
	 * @param pic - the given image
	 * @return - the greyscaled version of the image
	 */
	public static int[][][] greyScaled (int[][][] pic) 
	{
		// Replace the following statement with your code
		int[][][] grey=new int[pic.length][pic[0].length][pic[0][0].length];
		for (int i=0; i<pic.length; i++)
			for (int j=0; j<pic[i].length; j++)
				grey[i][j]=ImageOps.luminance(pic[i][j]);
		return grey;
	}	
	
	/**
	 * Creates a blurred version of an image.
	 * Uses a block blur algorithm.
	 * 
	 * @param pic - the given image
	 * @return - the blurred version of the image
	 */
	public static int[][][] blurred (int[][][] pic)
	{
		// Replace the following statement with your code
		int row=pic.length;
		int col=pic[0].length;
		int[][][] PicPad=new int[row+2][col+2][3];
		int[][][] blurredPic=new int[row][col][3];
		for (int i=0; i<PicPad.length; i++)
			for (int j=0; j<PicPad[i].length; j++)
				for (int t=0; t<PicPad[i][j].length; t++)
					if (i==0 || i==PicPad.length-1 || j==0 || j==PicPad[row].length-1)
						PicPad[i][j][t]=0;
					else
						PicPad[i][j][t]=pic[i-1][j-1][t];
		for (int i=1; i<PicPad.length-1; i++)
			for (int j=1; j<PicPad[i].length-1; j++)
				for (int t=0; t<PicPad[i][j].length; t++)
					ImageOps.blurColor(PicPad, blurredPic, i, j, t);
		return blurredPic;
	}

	// Blurs a given color of a given pixel in a given image.
	// Stores the result in a blurred version of the given image, without effecting the given image.
	// Uses a block blur algorithm.
	// pic - the given image
	// blurredPic - the blurred version of the given image
    // row - the row of the pixel
	// col - the column of the pixel
	// color - the color to blur: 0-red, 1-green, 2-blue
	private static void blurColor(int[][][] PicPad, int[][][] blurredPic, int row, int col, int color) 
	{
		// Put your code here
		int count=0;
		int countpow=0;
		if (row!=0 && row!=PicPad.length-1 && col!=0 && col!=PicPad[0].length-1)
			for (int i=row-1; i<=row+1; i++)
				for (int j=col-1; j<=col+1; j++)
				{
					if (ImageOps.getColorIntensity(PicPad, i, j, color)!=-1)
					{
						count++;
						countpow+=PicPad[i][j][color];
					}
				}
		blurredPic[row-1][col-1][color]=countpow/count;
	}
	
	// Returns the color intensity of a pixel, or -1 if the coordinates of the pixel are illegal.
	// pic - the given source image
	// row - the given row of the pixel
	// col - the given column of the pixel
	// color - the given color: 0-red, 1-green, 2-blue
	private static int getColorIntensity(int[][][] PicPad, int row, int col, int color) 
	{
		// Replace the following statement with your code
		if ((row!=0 && row!=PicPad.length-1) && (col!=0 && col!=PicPad[row].length-1))
		{
			return PicPad[row][col][color];
		}
		return -1;
	}
	
	/**
	 * Calculates the horizontal gradient of the greyscaled version of an image
	 * 
	 * @param pic - the given image
	 * @return - the gradient of the greyscaled version of the given image.
	 */
	public static int[][] horizontalGradient(int[][][] pic) 
	{
		// Replace the following statement with your code
		int row=pic.length;
		int col=pic[0].length;
		int [][] horpic=new int[row][col];
		for (int i=0; i<row; i++)
			for (int j=0; j<col; j++)
				if (j==0 || j==pic[0].length-1)
					horpic[i][j]=0;
				else
					horpic[i][j]=pic[i][j+1][0]-pic[i][j-1][0];
		return horpic;
	}
	
	/**
	 * Normalizes a 2D array so that all the values are between 0 to 255
	 * SIDE EFFECT: Changes the given array
	 * 
	 * @param arr - the given array
	 */
	public static void normalize(int[][] arr) 
	{
		// Put your code here
		int vmin=0;
		int vmax=0;
		for (int i=0; i<arr.length; i++)
			for (int j=0; j<arr[0].length; j++)
			{
				vmin=Math.min(vmin, arr[i][j]);
				vmax=Math.max(vmax, arr[i][j]);
			}
		for (int i=0; i<arr.length; i++)
			for (int j=1; j<arr[0].length-1; j++)
				arr[i][j]=(arr[i][j]-vmin)*vmax/(vmax-vmin);
	}
	
	/**
	 * Creates a greyscaled image showing the horizontal edges of a given image.
	 * Uses gradient edge detection.
	 * 
	 * @param pic - the given image
	 * @return - a greyscaled image showing the horizontal edges of the given image
	 */
	public static int[][][] edges(int[][][] pic) 
	{
		// Replace the following statement with your code
		pic=ImageOps.greyScaled(pic);
		ImageOps.show(pic);
		int[][] normgrat=ImageOps.horizontalGradient(pic);
		ImageOps.normalize(normgrat);
		int[][][] normgratfull=new int[pic.length][pic[0].length][pic[0][0].length];
		for (int i=0; i<normgratfull.length; i++)
			for (int j=0; j<normgratfull[i].length; j++)
				for (int t=0; t<normgratfull[i][j].length; t++)
					normgratfull[i][j][t]=normgrat[i][j];
		return normgratfull;
	}

	public static void main(String[] args) 
	{
		/**
		StdDraw.setCanvasSize(300, 300);
		// *** Testing the reading of an image from a file
		// Reads image data from a file, into an array
		int[][][] pic = read("xmen.ppm");
		//ImageOps.flipHorizontally(pic);
		//ImageOps.flipVertically(pic);
		//pic=ImageOps.greyScaled(pic);
		// Displays the image matrix (the function is described below)
		showData(pic);
		// Displays the image
		show(pic);
		*/
	}
}
