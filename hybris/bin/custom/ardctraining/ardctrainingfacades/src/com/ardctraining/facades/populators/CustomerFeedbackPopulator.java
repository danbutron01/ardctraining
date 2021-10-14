package com.ardctraining.facades.populators;


import com.ardctraining.core.model.CustomerFeedbackModel;
import com.ardctraining.facades.feedback.data.CustomerFeedbackData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class CustomerFeedbackPopulator implements Populator<CustomerFeedbackModel, CustomerFeedbackData> {
    @Override
    public void populate(CustomerFeedbackModel customerFeedbackModel, CustomerFeedbackData customerFeedbackData) throws ConversionException {
        customerFeedbackData.setMessage(customerFeedbackModel.getMessage());
        customerFeedbackData.setSubject(customerFeedbackModel.getSubject());
        customerFeedbackData.setSubmittedDate(customerFeedbackModel.getSubmittedDate());
        customerFeedbackData.setStatus(customerFeedbackModel.getStatus());
    }
}
