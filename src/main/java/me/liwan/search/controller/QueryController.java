package me.liwan.search.controller;

import me.liwan.search.utils.TopKQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@EnableConfigurationProperties({TopKQuery.class})
public class QueryController {

    @Autowired
    private TopKQuery topKQuery;

    @RequestMapping(value = "/api/query-recom", method = RequestMethod.GET)
    public String queryRecommend(@RequestParam("queryPre") String queryPre){
        log.info("path: [ /query-recom ], query prefix: [ " + queryPre + " ]. ");
        return topKQuery.getTrieBuilder().getTopKQueriesByPrefix(queryPre).toString();
    }
}
