import models.WList;
import models.WTask;
//import retrofit2.*;
import retrofit2.*;
import converter.*;
//
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Wund{
	String client_key = "86fbf1f67c17a31b6df7";
	String access_code = "";
	Retrofit retrofit;

	public Wund(String access_code){
		this.access_code = access_code;
		retrofit = new Retrofit.Builder()
				.baseUrl("https://a.wunderlist.com/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
	}

	public List<WList> getAllLists(){
		Api service = retrofit.create(Api.class);
		Call<List<WList>> call = service.getAllLists(access_code, client_key);
		List<WList> l = null;
		try {
			Response<List<WList>> response = call.execute();
			if(response != null){
				l = response.body();
			}
		} catch (IOException e) {
			return null;
		}
		return l;
	}

	public WList getList(String name){
		List<WList> allLists = getAllLists();
		if (allLists == null){
			return  null;
		} else {
			for(WList l : allLists){
				if(name.equalsIgnoreCase(l.getTitle())){
					return l;
				}
			}
		}
		return null;
	}

	public void addList(String name) throws IOException {
		String url= "https://a.wunderlist.com/api/v1/lists?client_id=" + client_key + "&access_token=" + access_code;
		URL object = null;
		try {
			object = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		HttpURLConnection con = (HttpURLConnection) object.openConnection();
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod("POST");

		OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
		wr.write("{\"title\":\""+name+"\"}");
		wr.flush();
	}

	public String nameToId(String name){
		List<WList> lists = getAllLists();
		if(lists == null){
			return null;
		}
		for(WList l : lists){
			if(name.equalsIgnoreCase(l.getTitle())){
				return "" + l.getId();
			}
		}
		return null;
	}

	public List<WTask> getAllTasks(String name){
		String id = nameToId(name);
		Api service = retrofit.create(Api.class);
		Call<List<WTask>> call = service.getAllTasks(id, access_code, client_key);
		try {
			Response<List<WTask>> r  = call.execute();
			return r.body();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void createTask(String listName, String taskName, String recType, int recCount) throws IOException {
		String listId = nameToId(listName);
		if(listId == null){
			return;
		}
		String url= "https://a.wunderlist.com/api/v1/tasks?client_id=" + client_key + "&access_token=" + access_code;
		URL object = null;
		try {
			object = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		HttpURLConnection con = (HttpURLConnection) object.openConnection();
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod("POST");

		OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
		wr.write("{\"list_id\":"+listId+", \"title\":\""+taskName+"\"}");
		wr.flush();
	}

}
