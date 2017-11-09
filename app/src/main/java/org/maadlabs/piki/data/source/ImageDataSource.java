package org.maadlabs.piki.data.source;

import org.maadlabs.piki.domain.entity.ImageData;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by brainfreak on 10/10/17.
 */

public interface ImageDataSource {

    Observable<List<ImageData>> search(String query, int limit);

    Observable<List<ImageData>> trending(int limit, int offset);
}
