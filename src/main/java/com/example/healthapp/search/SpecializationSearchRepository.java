package com.example.healthapp.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.List;

public interface SpecializationSearchRepository extends ElasticsearchRepository<SpecializationIndex, String> {

    List<SpecializationIndex> findByNameContainingIgnoreCase(String name);
}
