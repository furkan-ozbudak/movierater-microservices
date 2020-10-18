package com.furkanozbudak.moviecatalogservice;

import com.furkanozbudak.moviecatalogservice.model.CatalogItem;
import com.furkanozbudak.moviecatalogservice.model.Movie;
import com.furkanozbudak.moviecatalogservice.model.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {

        // create rest template utility object to call other microservices
        RestTemplate restTemplate = new RestTemplate();
        Movie movie = restTemplate.getForObject("http://localhost:8081/movies/foo", Movie.class);


        // get all rated movie ids
        List<Rating> ratings = Arrays.asList(new Rating("1234", 4),
                new Rating("5678", 3));

        // for each movie ID, call movie info service and get details

        return ratings.stream().map(rating -> new CatalogItem("Transofrmets", "Test", 4))
                .collect(Collectors.toList());

        // put them all together


        //return Collections.singletonList(new CatalogItem("Transformers", "Test", 4));
    }
}
