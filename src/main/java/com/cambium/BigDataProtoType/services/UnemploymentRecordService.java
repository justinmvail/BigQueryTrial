package com.cambium.BigDataProtoType.services;

import com.cambium.BigDataProtoType.beans.UnemploymentRecord;
import com.cambium.BigDataProtoType.beans.YearRange;
import com.cambium.BigDataProtoType.converters.FieldValueListToUnemploymentRecordConverter;
import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.TableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UnemploymentRecordService {

    private final BigDataService bigDataService;
    private final FieldValueListToUnemploymentRecordConverter recordConverter;

    @Autowired
    public UnemploymentRecordService(BigDataService bigDataService, FieldValueListToUnemploymentRecordConverter recordConverter) {
        this.bigDataService = bigDataService;
        this.recordConverter = recordConverter;
    }

    public List<UnemploymentRecord> getUnemploymentRecords(YearRange yearRange) throws IOException, InterruptedException {
        String rawUnemploymentSql = "SELECT * FROM `bigquery-public-data.bls.unemployment_cps` where year >= %tY and year <= %tY order by date";
        String preparedUnemploymentSql = String.format(rawUnemploymentSql, yearRange.getStartingYear(), yearRange.getEndingYear());
        TableResult tableResult = bigDataService.query(preparedUnemploymentSql);

        List<UnemploymentRecord> unemploymentRecordList = new ArrayList<>();
        for (FieldValueList row : tableResult.iterateAll()) {
            unemploymentRecordList.add(recordConverter.convert(row));
        }
        return unemploymentRecordList;
    }

}
