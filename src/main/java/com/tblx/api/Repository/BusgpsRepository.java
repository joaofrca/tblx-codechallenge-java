package com.tblx.api.Repository;

import com.tblx.api.Model.Busgps;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusgpsRepository extends MongoRepository<Busgps, Long> {
}
