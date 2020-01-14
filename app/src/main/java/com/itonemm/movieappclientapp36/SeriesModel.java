package com.itonemm.movieappclientapp36;

public class SeriesModel {
    String seriesName;
    String seriesImage;
    String seriesVideo;
    String seriesCategory;

    public SeriesModel(String seriesName, String seriesImage, String seriesVideo, String seriesCategory) {
        this.seriesName = seriesName;
        this.seriesImage = seriesImage;
        this.seriesVideo = seriesVideo;
        this.seriesCategory = seriesCategory;
    }

    public SeriesModel() {
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getSeriesImage() {
        return seriesImage;
    }

    public void setSeriesImage(String seriesImage) {
        this.seriesImage = seriesImage;
    }

    public String getSeriesVideo() {
        return seriesVideo;
    }

    public void setSeriesVideo(String seriesVideo) {
        this.seriesVideo = seriesVideo;
    }

    public String getSeriesCategory() {
        return seriesCategory;
    }

    public void setSeriesCategory(String seriesCategory) {
        this.seriesCategory = seriesCategory;
    }
}
