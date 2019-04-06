package fn.practice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class PropertyLoader {

	private final Map<String, String> countryMap = new HashMap<>();
	private final ScheduledExecutorService scheduler;
	String propertyLocation;
	Long lInterval =10L;

	PropertyLoader(){
		scheduler = Executors.newScheduledThreadPool(1);
		propertyLocation = System.getProperty("java.class.path")+"/resources/config.properties";
		
		getClass();
	}


	public void init() {
		loadCache();
		WriterTask task = new WriterTask();
		scheduler.scheduleAtFixedRate(task,lInterval,50,TimeUnit.SECONDS);
		
	}

	private void loadCache(){

		Properties properties = new Properties();
		InputStream inStream = null;
		try {
			inStream = new FileInputStream(propertyLocation);
			//inStream = getClass().getClassLoader().getResourceAsStream(propertyLocation);
			properties.load(inStream);
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		for (Object key : properties.keySet()){
			Object value = properties.getProperty(key.toString());
			countryMap.put(key.toString(),value.toString() );
		}
		properties=null;
	}

	private void writeToFile(){

		System.out.println("Begin writeToFile" + countryMap);
		Properties properties = new Properties();
		InputStream inStream = null;
		OutputStream outStream = null;
		try {
			inStream = new FileInputStream(propertyLocation);
			//inStream = getClass().getClassLoader().getResourceAsStream(propertyLocation);
			properties.load(inStream);

		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		Map<String, String> missingUserMap = properties.entrySet().stream()
				.filter(e -> !countryMap.containsKey(e.getKey()))
				.collect(Collectors.toMap(a->(String)a.getKey(), a->(String)a.getValue()));
				
				//new HashMap<>();
		/*for (Object key : properties.keySet()){
			Object value = properties.getProperty(key.toString());
			if (!countryMap.containsKey(key.toString())){
				missingUserMap.put(key.toString(),value.toString());				
			}
		}
*/
		for (String key : countryMap.keySet()){
			if (!properties.containsKey(key)){
				System.out.println("Discovered new user "+ key);
				properties.put(key, countryMap.get(key));
			}
		}

		try{
			outStream = new FileOutputStream(propertyLocation);
			//outStream = getClass().getClassLoader().getResourceAsStream(propertyLocation);
			properties.store(outStream, null);
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (outStream != null) {
				try {
					outStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		countryMap.putAll(missingUserMap);
		properties=null;
		System.out.println("End writeToFile");
	}

	class WriterTask implements Runnable{

		@Override
		public void run() {
			//System.out.println("#WriterTask Start backing up cache in File ..."+ new Date());
			writeToFile();
			//System.out.println("#WriterTask Completed backing up cache in File ..."+ new Date());
		}

	}
	public static void main(String[] args) {
		PropertyLoader pl = new PropertyLoader();
		pl.init();

	}

}
