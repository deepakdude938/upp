package com.upp.hooks;

import com.upp.base.BaseClass;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestRunStarted;

public class CustomReportListener extends BaseClass implements EventListener {


	public CustomReportListener() {
	};

	@Override
	public void setEventPublisher(EventPublisher publisher) {

		
		publisher.registerHandlerFor(TestRunStarted.class, this::runStarted);
		publisher.registerHandlerFor(TestRunFinished.class, this::runFinished);

	};

	
	private void runStarted(TestRunStarted event) {

	};

// TestRunFinished event is triggered when all feature file executions are
// completed
	private void runFinished(TestRunFinished event) {
		
	System.out.println("totalTestcasesCount:"+(Hook.getPassedCount()+Hook.getFailedCount()+Hook.getSkippedCount()));
	System.out.println("passedTestcasesCount:"+Hook.getPassedCount());
	System.out.println("failedTestcasesCount:"+Hook.getFailedCount());
	System.out.println("skippedTestcasesCount:"+Hook.getSkippedCount());
	};

}