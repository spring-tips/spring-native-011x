package com.example.batch;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Log4j2
@EnableBatchProcessing
@SpringBootApplication
public class BatchApplication {

	@Bean
	Step step(StepBuilderFactory stepBuilderFactory) {
		return stepBuilderFactory
			.get("step")
			.tasklet((stepContribution, chunkContext) -> {
				log.info("Hello, Spring Native!");
				return RepeatStatus.FINISHED;
			})
			.build();
	}

	@Bean
	Job job(JobBuilderFactory jobBuilderFactory, Step step) {
		return jobBuilderFactory
			.get("job")
			.start(step)
			.build();
	}

	@SneakyThrows
	public static void main(String[] args) {
		SpringApplication.run(BatchApplication.class, args);
		Thread.currentThread().join();
	}

}
