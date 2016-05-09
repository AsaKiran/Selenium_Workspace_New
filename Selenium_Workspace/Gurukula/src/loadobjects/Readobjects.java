package loadobjects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Readobjects 
{
	
Properties objrep= new Properties();

public Properties readobjproperties() 
{
	try	
	{
	
	FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\testinputs\\objRepository\\ObjectRepository.properties");
	objrep.load(fis);
	return objrep;
	}
	
	catch(FileNotFoundException f  )
	
	{
		System.out.println("Exception:Unable to Open file:"+f);
		return null;
			
	}
			
	catch(IOException io)
	{
		System.out.println("IO exception:"+io);	
		return null;
	}
	
}

}
	
	
