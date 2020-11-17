package com.tblx.api.Repositories;

import com.tblx.api.Model.Busgps;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//@RepositoryRestResource(collectionResourceRel = "people", path = "people")
@Repository
public interface BusgpsRepository extends MongoRepository<Busgps, Long> {

	List<Busgps> findByDelay(@Param("delay") String delay);

}
