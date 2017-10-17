package org.maadlabs.piki.data.net.imgur;

import org.maadlabs.piki.data.net.imgur.model.ImgurSearchResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by brainfreak on 10/16/17.
 */

public interface ImgurAPI {

    @Headers("Authorization: Client-ID b23fe7cd378c3e0")
    @GET("/search/{sort}")
    Call<List<ImgurSearchResponse>> search(@Path("sort") String sortBy, @Query("q") String query);

    @Headers("Authorization: Client-ID b23fe7cd378c3e0")
    @GET("/search/{sort}/{window}")
    Call<List<ImgurSearchResponse>> search(@Path("sort") String sortBy, @Path("window") String window, @Query("q") String query);

    @Headers("Authorization: Client-ID b23fe7cd378c3e0")
    @GET("/search/{sort}/{window}/{page}")
    Call<List<ImgurSearchResponse>> search(@Path("sort") String sortBy, @Path("window") String window, @Path("page") int page, @Query("q") String query);
}
