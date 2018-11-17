package com.cambium.BigDataProtoType.repositories;

import com.cambium.BigDataProtoType.beans.UnemploymentRecord;
import org.springframework.data.repository.CrudRepository;

import java.time.Year;
import java.util.List;

public interface UnemploymentRecordRepository extends CrudRepository<UnemploymentRecord, Long> {

    List<UnemploymentRecord> findAllByYearGreaterThanEqualAndYearLessThanEqual(Year startingYear, Year endingYear);

}
