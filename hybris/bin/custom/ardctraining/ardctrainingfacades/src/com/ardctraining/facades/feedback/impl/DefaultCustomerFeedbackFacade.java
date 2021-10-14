package com.ardctraining.facades.feedback.impl;

import com.ardctraining.core.feedback.dao.CustomerFeedbakDao;
import com.ardctraining.core.feedback.dao.CustomerFeedbakDao;
import com.ardctraining.core.feedback.service.CustomerFeedbackService;
import com.ardctraining.core.model.CustomerFeedbackModel;
import com.ardctraining.facades.feedback.CustomerFeedbackFacade;
import com.ardctraining.facades.feedback.data.CustomerFeedbackData;
import de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class DefaultCustomerFeedbackFacade extends DefaultCustomerFacade implements CustomerFeedbackFacade {
    private CustomerFeedbackService customerFeedbackService;

    private Converter<CustomerFeedbackModel,CustomerFeedbackData> customerFeedbackConverter;



    @Override
    public List<CustomerFeedbackData> getUnread() {
        final CustomerModel currentCustomer = (CustomerModel) getUserService().getCurrentUser();
        List<CustomerFeedbackModel> feedbackUnread =getCustomerFeedbackService().findByCustomerAndState(currentCustomer);

        return (Objects.nonNull(currentCustomer) && currentCustomer instanceof CustomerModel)?getCustomerFeedbackConverter().convertAll(feedbackUnread):Collections.emptyList();
    }

    @Override
    public void save(String subject,String message){
        CustomerFeedbackModel model = new CustomerFeedbackModel();
        model.setSubject(subject);
        model.setMessage(message);
        customerFeedbackService.save(model);
    }

    public CustomerFeedbackService getCustomerFeedbackService() {
        return customerFeedbackService;
    }

    public void setCustomerFeedbackService(CustomerFeedbackService customerFeedbackService) {
        this.customerFeedbackService = customerFeedbackService;
    }


    public Converter<CustomerFeedbackModel, CustomerFeedbackData> getCustomerFeedbackConverter() {
        return customerFeedbackConverter;
    }

    public void setCustomerFeedbackConverter(Converter<CustomerFeedbackModel, CustomerFeedbackData> customerFeedbackConverter) {
        this.customerFeedbackConverter = customerFeedbackConverter;
    }
}
