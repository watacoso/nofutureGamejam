package nofutureball;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/*import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;*/

public class EntityProps{
	
	public int g1;
	public int g2;
	public int g3;
	public int g4;
	
	public EntityProps(){
		
	}
	
	public void loadJson(String src){
		
		// I commented out these because they made pre-compile errors.
		// I guess there's a library I need! - Ploppz
		
		/*JsonReader reader = null;
		try {
			reader = new JsonReader(
			        new InputStreamReader(new FileInputStream(src)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		EntityProps ent = gson.fromJson(reader, EntityProps.class);
		*/
	}
}
