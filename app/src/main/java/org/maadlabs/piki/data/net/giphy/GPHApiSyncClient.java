package org.maadlabs.piki.data.net.giphy;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.giphy.sdk.core.models.enums.LangType;
import com.giphy.sdk.core.models.enums.MediaType;
import com.giphy.sdk.core.models.enums.RatingType;
import com.giphy.sdk.core.network.api.Constants;
import com.giphy.sdk.core.network.api.GPHApiClient;
import com.giphy.sdk.core.network.engine.NetworkSession;
import com.giphy.sdk.core.network.response.ListMediaResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import javax.inject.Inject;

/**
 * Created by brainfreak on 10/10/17.
 */

public class GPHApiSyncClient extends GPHApiClient implements GPHApiSync{

    private NetworkSession mNetworkSession;
    private String mApiKey;

    public GPHApiSyncClient(String apiKey) {
        super(apiKey);
        this.mApiKey = getApiKey();
        mNetworkSession = getNetworkSession();
    }

    public GPHApiSyncClient(String apiKey, NetworkSession session) {
        super(apiKey, session);
        apiKey = getApiKey();
        mNetworkSession = getNetworkSession();
    }



    @Nullable
    @Override
    public ListMediaResponse search(@NonNull String searchQuery, @Nullable MediaType type, @Nullable Integer limit, @Nullable Integer offset, @Nullable RatingType rating, @Nullable LangType lang) {

        final Map<String, String> params = new HashMap<>();
        params.put(API_KEY, mApiKey);
        params.put("q", searchQuery);
        if (limit != null) {
            params.put("limit", limit.toString());
        }
        if (offset != null) {
            params.put("offset", offset.toString());
        }
        if (rating != null) {
            params.put("rating", rating.toString());
        }
        if (lang != null) {
            params.put("lang", lang.toString());
        }

        try {
            return mNetworkSession.queryStringConnection(Constants.SERVER_URL,
                    String.format(Constants.Paths.SEARCH, mediaTypeToEndpoint(type)), HTTP_GET,
                    ListMediaResponse.class, params, null).executeImmediately();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    @Override
    public ListMediaResponse trending(@Nullable MediaType type, @Nullable Integer limit, @Nullable Integer offset, @Nullable RatingType rating) {

        final Map<String, String> params = new HashMap<>();
        params.put(API_KEY, mApiKey);
        if (limit != null) {
            params.put("limit", limit.toString());
        }
        if (offset != null) {
            params.put("offset", offset.toString());
        }
        if (rating != null) {
            params.put("rating", rating.toString());
        }
        try {
            ListMediaResponse response =  mNetworkSession.queryStringConnection(Constants.SERVER_URL,
                    String.format(Constants.Paths.TRENDING, mediaTypeToEndpoint(type)), HTTP_GET,
                    ListMediaResponse.class, params, null).executeImmediately();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @NonNull
    private String mediaTypeToEndpoint(@Nullable  MediaType type) {
        if (type == MediaType.sticker) {
            return "stickers";
        } else {
            return "gifs";
        }
    }

    public void setApiKey(String apiKey) {
        mApiKey = apiKey;
    }
}
