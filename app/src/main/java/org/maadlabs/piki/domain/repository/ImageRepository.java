package org.maadlabs.piki.domain.repository;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by brainfreak on 10/10/17.
 */

public interface ImageRepository {

    Observable<List<String>> search(String query, int limit);
}
