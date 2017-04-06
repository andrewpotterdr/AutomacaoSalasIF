package clientes;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/** 
 * @author Unknown
 * @version Unknown
 * Classe para adquirir os endereços IP e MAC do dispositivo corrente.
 */

public class GetNetworkAddress
{

	/**
	 * Método principal de execução da classe.
	 * @param args
	 */
	
   public static void main(String[] args)
   {
	   
   }
   
   /**
    * Método que adquire e retorna o IP ou MAC dependendo do parâmetro passado.
    * @param addressType
    * @return String
    */

   public static String GetAddress(String addressType)
   {
       String address = "";
       InetAddress lanIp = null;
        try
        {

            String ipAddress = null;
            Enumeration<NetworkInterface> net = null;
            net = NetworkInterface.getNetworkInterfaces();
            while(net.hasMoreElements())
            {
                NetworkInterface element = net.nextElement();
                Enumeration<InetAddress> addresses = element.getInetAddresses();
                while (addresses.hasMoreElements())
                {
                    InetAddress ip = addresses.nextElement();
                    if (ip instanceof Inet4Address)
                    {

                        if (ip.isSiteLocalAddress())
                        {

                            ipAddress = ip.getHostAddress();
                            lanIp = InetAddress.getByName(ipAddress);
                        }

                    }

                }
            }

            if(lanIp == null) return null;

            if(addressType.equals("ip"))
            {

                address = lanIp.toString().replaceAll("^/+", "");

            }
            else if(addressType.equals("mac"))
            {

                address = GetMacAddress(lanIp);

            }
            else
            {

                throw new Exception("Specify \"ip\" or \"mac\"");

            }

        }
        catch (UnknownHostException e)
        {

            e.printStackTrace();

        }
        catch (SocketException e)
        {

            e.printStackTrace();

        }
        catch (Exception e)
        {

            e.printStackTrace();

        }

       return address;

   }
   
   /**
    * Método que retorna o endereço MAC da máquina.
    * @param ip
    * @return String
    */

   private static String GetMacAddress(InetAddress ip)
   {
       String address = null;
        try {

            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac = network.getHardwareAddress();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++)
            {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));        
            }
            address = sb.toString();

        }
        catch (SocketException e)
        {
            e.printStackTrace();
        }
        return address;
   	}

}