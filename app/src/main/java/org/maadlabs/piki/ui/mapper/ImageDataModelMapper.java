package org.maadlabs.piki.ui.mapper;

import org.maadlabs.piki.domain.entity.ImageData;
import org.maadlabs.piki.ui.model.ImageDataModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by brainfreak on 10/14/17.
 */

public class ImageDataModelMapper {

    @Inject
    public ImageDataModelMapper() {

    }

    public List<ImageDataModel> map(List<ImageData> imageDataList) {

        List<ImageDataModel> modelList = new ArrayList<>();
        for (ImageData data : imageDataList) {
            modelList.add(new ImageDataModel(data.getUrl()));
        }
        return modelList;
    }
}
