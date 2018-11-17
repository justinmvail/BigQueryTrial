package com.cambium.BigDataProtoType.converters;

import com.cambium.BigDataProtoType.beans.UnemploymentRecord;
import com.google.cloud.bigquery.FieldValue;
import com.google.cloud.bigquery.FieldValueList;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;

@Service
public class FieldValueListToUnemploymentRecordConverter {

    public UnemploymentRecord convert(FieldValueList row){
            String seriesId = getNullSafeFieldValue("series_id", row);
            Year year = Year.parse(getNullSafeFieldValue("year", row));
            String period = getNullSafeFieldValue("period", row);
            Float value = Float.parseFloat(getNullSafeFieldValue("value", row));
            String footnote_codes = getNullSafeFieldValue("footnote_codes", row);
            LocalDate date = LocalDate.parse(getNullSafeFieldValue("date", row));
            String seriesTitle = getNullSafeFieldValue("series_title", row);
            return new UnemploymentRecord(seriesId, year, period, value, footnote_codes, date, seriesTitle);
    }

    private String getNullSafeFieldValue(String fieldName, FieldValueList row){
        FieldValue fieldValue = row.get(fieldName);
        String strFieldValue;
        if (!fieldValue.isNull()){
            strFieldValue = fieldValue.getStringValue();
        }else{
            strFieldValue = "";
        }
        return strFieldValue;
    }

}
