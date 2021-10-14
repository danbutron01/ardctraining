package com.ardctraining.facades.feedback;

import com.ardctraining.core.model.CustomerFeedbackModel;
import com.ardctraining.facades.feedback.data.CustomerFeedbackData;

import java.util.List;


public interface CustomerFeedbackFacade {

    public List<CustomerFeedbackData> getUnread();

    public void save(String subject,String message);
}
