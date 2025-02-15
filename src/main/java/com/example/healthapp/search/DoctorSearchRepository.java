package com.example.healthapp.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.List;

public interface DoctorSearchRepository extends ElasticsearchRepository<DoctorIndex, String> {

    List<DoctorIndex> findByNameContainingIgnoreCase(String name);
}
