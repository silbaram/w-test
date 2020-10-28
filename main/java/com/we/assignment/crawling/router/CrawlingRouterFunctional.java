package com.we.assignment.crawling.router;

import com.we.assignment.crawling.handler.CrawlingHandlerFunctional;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;


@Component
public class CrawlingRouterFunctional {


    @Bean
    public RouterFunction<ServerResponse> crawlingRouter(CrawlingHandlerFunctional crawlingHandlerFunctional) {

        return RouterFunctions.route(GET("/crawling/converted"), crawlingHandlerFunctional::crawlingConvertedHandler);
    }
}
