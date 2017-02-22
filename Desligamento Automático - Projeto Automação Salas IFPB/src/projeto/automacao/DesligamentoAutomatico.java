package projeto.automacao;

public class DesligamentoAutomatico
{
    private static String OS = System.getProperty("os.name").toLowerCase();
    public static void main(String[] args) throws Exception
    {
    	if (isWindows())
    	{
    		String[] commandWin = new String[3];
    		commandWin[0] = "cmd";
    		commandWin[1] = "/c";
    		commandWin[2] = "shutdown -s";
    		Runtime.getRuntime().exec (commandWin);
        }
    	else if (isMac())
        {
    		String commandMac = "shutdown";
    		Runtime.getRuntime().exec (commandMac);
        }
    	else if (isUnix())
        {
    		String commandLin = "poweroff";
    		Runtime.getRuntime().exec (commandLin);
        }
    }
    public static boolean isWindows()
    {
        return (OS.indexOf("win") >= 0);
    }
    public static boolean isMac()
    {
        return (OS.indexOf("mac") >= 0);
    }
    public static boolean isUnix()
    {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
    }
}