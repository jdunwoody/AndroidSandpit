package com.camellia.search;

import java.util.ArrayList;
import java.util.List;

public class SearchService {

    public List<SearchResult> search(String name, String address) {

        List<SearchResult> result = new ArrayList<SearchResult>();
        result.add(new SearchResult("Adam Smith"));
        return result;
    }
}
