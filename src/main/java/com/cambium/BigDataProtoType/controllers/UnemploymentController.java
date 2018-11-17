package com.cambium.BigDataProtoType.controllers;

import com.cambium.BigDataProtoType.services.UnemploymentRecordService;
import com.cambium.BigDataProtoType.beans.UnemploymentRecord;
import com.cambium.BigDataProtoType.repositories.UnemploymentRecordRepository;
import com.cambium.BigDataProtoType.beans.YearRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;

@RestController
public class UnemploymentController {

    private final UnemploymentRecordRepository unemploymentRecordRepository;
    private final UnemploymentRecordService unemploymentRecordsService;

    @Autowired
    public UnemploymentController(UnemploymentRecordRepository unemploymentRecordRepository, UnemploymentRecordService queryForUnemploymentRecordsService) {
        this.unemploymentRecordRepository = unemploymentRecordRepository;
        this.unemploymentRecordsService = queryForUnemploymentRecordsService;
    }

    @RequestMapping(
            value = "/unemployment",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json")
    List<UnemploymentRecord> getUnemploymentData(@RequestBody YearRange yearRange) throws IOException, InterruptedException {
        List<UnemploymentRecord> unemploymentRecords = unemploymentRecordsService.getUnemploymentRecords(yearRange);
        unemploymentRecordRepository.saveAll(unemploymentRecords);
        return unemploymentRecords;
    }

    @RequestMapping(
            value = "/unemployment/cache",
            method = RequestMethod.GET,
            consumes = "application/json",
            produces = "application/json")
    List<UnemploymentRecord> getCachedUnemploymentData(@RequestBody YearRange yearRange) throws IOException, InterruptedException {
        return unemploymentRecordRepository.findAllByYearGreaterThanEqualAndYearLessThanEqual(yearRange.getStartingYear(), yearRange.getEndingYear());
    }
}
