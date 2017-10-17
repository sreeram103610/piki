package org.maadlabs.piki.data.net.imgur.model;

import okhttp3.OkHttpClient;

/**
 * Created by brainfreak on 10/16/17.
 */

public class ImgurSearchRequest {

    String query;
    SortBy sort;
    SearchWindow window;
    int page;

    public enum SearchWindow {
        DAY, WEEK, MONTH, YEAR, ALL
    }

    public enum SortBy {
        TIME, VIRAL, TOP
    }

    public class Builder {

        ImgurSearchRequest mSearchRequest;

        public Builder() {
            mSearchRequest = new ImgurSearchRequest();
        }

        public Builder query(String query) {
            mSearchRequest.query = query.trim();
            return this;
        }

        public Builder sortBy(SortBy sortBy) {
            mSearchRequest.sort = sortBy;
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
}
