package Utils;

public class Log {

	public static void debugConsole(String msg)
	{
		System.out.println(msg);
	}
	
	public static void debugConsole(String msg, boolean newLine)
	{
		if(newLine)
		{
			debugConsole(msg);
		}else {
			System.out.print(msg);
		}
	}
	
	public static void debugConsole(double msg)
	{
		System.out.println(msg);
	}
	
	public static void errorConsole( Exception ex)
	{
		System.err.println(ex);
	}
}
