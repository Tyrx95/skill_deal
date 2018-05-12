package com.skilldealteam.skilldeal.helpers;

public class SearchFilter {

    public String queryString;
    public String searchBy;
    public String sortBy;
    public Boolean videoLesson;
    public Boolean liveMeeting;

    public SearchFilter(){}

    public static SearchFilter createSearchFilter(){
        return new SearchFilter();
    }

    public SearchFilter setQueryString(String queryString) {
        this.queryString = queryString;
        return this;
    }

    public SearchFilter setSearchBy(String searchBy) {
        this.searchBy = searchBy;
        return this;
    }

    public SearchFilter setSortBy(String sortBy) {
        this.sortBy = sortBy;
        return this;
    }

    public SearchFilter setVideoLesson(Boolean videoLesson) {
        this.videoLesson = videoLesson;
        return this;
    }

    public SearchFilter setLiveMeeting(Boolean liveMeeting) {
        this.liveMeeting = liveMeeting;
        return this;
    }
}
