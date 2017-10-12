package org.maadlabs.piki.data.net.giphy;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.giphy.sdk.core.models.enums.LangType;
import com.giphy.sdk.core.models.enums.MediaType;
import com.giphy.sdk.core.models.enums.RatingType;
import com.giphy.sdk.core.network.api.GPHApi;
import com.giphy.sdk.core.network.response.ListMediaResponse;

/**
 * Created by brainfreak on 10/10/17.
 */

public interface GPHApiSync extends GPHApi {


    /**
     * Search for gifs or stickers
     * @param searchQuery search query term or phrase
     * @param type can be sticker or gif
     * @param limit (optional) number of results to return, maximum 100. Default 25.
     * @param offset (optional) results offset, defaults to 0.
     * @param rating (optional) limit results to those rated (y,g, pg, pg-13 or r).
     * @param lang  (optional) specify default country for regional content; format is 2-letter ISO 639-1 country code.
     * @return ListMediaResponse object of the requested search query
     */
    @NonNull
    public ListMediaResponse search(@NonNull String searchQuery, @Nullable MediaType type, @Nullable Integer limit,
                                    @Nullable Integer offset, @Nullable RatingType rating,
                                    @Nullable LangType lang);
}
