package com.example.healthapp.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.List;

public interface PracticeSearchRepository extends ElasticsearchRepository<PracticeIndex, String> {

    List<PracticeIndex> findByNameContainingIgnoreCase(String name);
}
