package org.maadlabs.piki.domain.repository;

import org.maadlabs.piki.domain.entity.ImageData;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by brainfreak on 10/10/17.
 */

public interface ImageRepository {

    Observable<List<ImageData>> search(String query, int limit);
}
