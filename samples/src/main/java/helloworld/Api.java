import models.WList;
import models.WTask;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

public interface Api {

    @GET("api/v1/lists")
    Call<List<WList>> getAllLists(
            @Query("access_token") String access_token,
            @Query("client_id") String client_id
    );

    @POST("api/v1/lists")
    Call<RequestBody> addList(
            @Body RequestBody rb,
            @Query("access_token") String access_token,
            @Query("client_id") String client_id
    );

    @GET("api/v1/tasks")
    Call<List<WTask>> getAllTasks(
            @Query("list_id") String list_id,
            @Query("access_token") String access_token,
            @Query("client_id") String client_id
    );

}