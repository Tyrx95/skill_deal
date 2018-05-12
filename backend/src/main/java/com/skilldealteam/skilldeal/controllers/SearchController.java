package com.skilldealteam.skilldeal.controllers;

import com.skilldealteam.skilldeal.helpers.SearchFilter;
import com.skilldealteam.skilldeal.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController extends BaseController {

    @Autowired
    private SearchService service;

    private static final String QUERY_STRING = "q";
    private static final String SEARCH_BY = "search_by";
    private static final String SORT_BY = "sort_by";
    private static final String VIDEO_LESSON = "video_lesson";
    private static final String LIVE_MEETING = "live_meeting";



    @RequestMapping(value = "/api/search", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity search(
            @RequestParam(name = QUERY_STRING) String queryString,
            @RequestParam(name = SEARCH_BY) String searchBy,
            @RequestParam(name = SORT_BY) String sortBy,
            @RequestParam(name = VIDEO_LESSON, required = false) Boolean videoLesson,
            @RequestParam(name = LIVE_MEETING, required = false) Boolean liveMeeting
            ) {
        return wrapForPublic(() ->
                this.service.search(
                        SearchFilter.createSearchFilter()
                                .setSearchBy(searchBy)
                                .setQueryString(queryString)
                                .setSortBy(sortBy)
                                .setVideoLesson(videoLesson)
                                .setLiveMeeting(liveMeeting)));
    }

}
