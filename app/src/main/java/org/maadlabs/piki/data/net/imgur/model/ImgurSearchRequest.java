package org.maadlabs.piki.data.net.imgur.model;

import okhttp3.OkHttpClient;

/**
 * Created by brainfreak on 10/16/17.
 */

public class ImgurSearchRequest {


    private static final String BASE_URL = "/3/gallery/search";
    String requestUrl;
    String query;
    SortBy sort;
    SearchWindow window;
    int page;

    private ImgurSearchRequest() {

    }

    public enum SearchWindow {
        DAY, WEEK, MONTH, YEAR, ALL
    }

    public enum SortBy {
        TIME, VIRAL, TOP
    }

    public static class Builder {

        ImgurSearchRequest mSearchRequest;

        public Builder() {
            mSearchRequest = new ImgurSearchRequest();
        }

        public Builder query(String query) {
            if(query != null)
                mSearchRequest.query = query.trim();
            return this;
        }

        public Builder sortBy(SortBy sortBy) {
            mSearchRequest.sort = sortBy;
            return this;
        }

        public Builder page(int page) {
            mSearchRequest.page = page;
            return this;
        }

        public Builder window(SearchWindow window) {
            mSearchRequest.window = window;
            return this;
        }

        public ImgurSearchRequest build() {
            return mSearchRequest;
        }
    }

    public String buildUrl(boolean rebuild) {

        if (requestUrl != null && !rebuild)
            return requestUrl;

        StringBuilder builder = new StringBuilder(BASE_URL);
        if (sort != null)
            builder.append("/" + sort.name());
        if (window != null)
            builder.append("/" + window.name());
        if (page > 0)
            builder.append("/" + page);
        if (query != null)
            builder.append("?q=" + query);

        requestUrl = builder.toString();
        return requestUrl;
    }
}
