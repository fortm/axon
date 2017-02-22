package com.example.axon;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

@Aggregate
public class Complaint {

	@AggregateIdentifier
	private String complaintId;

	public Complaint() {
	}

	@CommandHandler
	public Complaint(FileComplaintCommand command) {
		Assert.hasLength(command.getCompany());
		apply(new ComplaintFiledEvent(command.getId(), command.getCompany(), command.getDescription()));
	}

	@EventSourcingHandler
	protected void on(ComplaintFiledEvent event) {
		this.complaintId = event.getId();
	}

}