package com.cambium.BigDataProtoType.services;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobId;
import com.google.cloud.bigquery.JobInfo;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.TableResult;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class BigDataService {

    TableResult query(String sql) throws IOException, InterruptedException {
        BigQuery bigQuery = getBigQueryInstance();
        QueryJobConfiguration queryJobConfiguration =
                QueryJobConfiguration.newBuilder(sql)
                        .setUseLegacySql(false)
                        .build();
        JobId jobId = JobId.of(UUID.randomUUID().toString());
        return runQuery(bigQuery, queryJobConfiguration, jobId);
    }

    private BigQuery getBigQueryInstance() throws IOException {
        //todo: move projectid and key.json references to properties file
        return BigQueryOptions.newBuilder()
                .setProjectId("my-project-1534528276094")
                .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream("key.json")))
                .build()
                .getService();
    }

    private TableResult runQuery (BigQuery bigQuery, QueryJobConfiguration queryJobConfiguration, JobId jobId) throws InterruptedException {
        Job job = bigQuery.create(JobInfo.newBuilder(queryJobConfiguration).setJobId(jobId).build());
        job = job.waitFor();
        checkJobForErrors(job);
        return job.getQueryResults();
    }

    private void checkJobForErrors(Job job){
        if (job == null) {
            throw new RuntimeException("Job no longer exists");
        } else if (job.getStatus().getError() != null) {
            throw new RuntimeException(job.getStatus().getError().toString());
        }
    }
}
