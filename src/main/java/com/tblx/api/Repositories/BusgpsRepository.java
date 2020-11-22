package com.tblx.api.Repositories;

import com.tblx.api.Model.Busgps;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusgpsRepository extends MongoRepository<Busgps, Long> {

	List<Busgps> findByTimestampBetween(@Param("timestamp") long starttime,
										@Param("timestamp") long endtime);

	List<Busgps> findByTimestampBetweenAndOperator(@Param("timestamp") long starttime,
												   @Param("timestamp") long endtime,
												   @Param("operator") String operator);

	List<Busgps> findByTimestampBetweenAndVehicleID(@Param("timestamp") long starttime,
												   @Param("timestamp") long endtime,
												   @Param("vehicleID") int vehicleID);

}
