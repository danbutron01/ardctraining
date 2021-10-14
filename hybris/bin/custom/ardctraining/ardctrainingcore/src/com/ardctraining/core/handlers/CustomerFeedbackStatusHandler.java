package com.ardctraining.core.handlers;

import com.ardctraining.core.enums.FeedbackStatusEnum;
import com.ardctraining.core.model.CustomerFeedbackModel;
import de.hybris.bootstrap.ddl.PropertiesLoader;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;
import de.hybris.platform.servicelayer.time.TimeService;
import org.apache.commons.lang.BooleanUtils;
import org.joda.time.DateTime;


import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;


public class CustomerFeedbackStatusHandler implements DynamicAttributeHandler<FeedbackStatusEnum, CustomerFeedbackModel> {

    @Resource(name = "defaultConfigurationService")
    private ConfigurationService configurationService;

    private TimeService timeService;


    @Override
    public FeedbackStatusEnum get(CustomerFeedbackModel model) {
        int days = getConfigurationService().getConfiguration().getInt("feedback.status.duedate.calculation.days.thresthold");
        DateTime now = new DateTime();
        DateTime writeDayDateTime = new DateTime(model.getSubmittedDate());
        DateTime limitDate = writeDayDateTime.plusDays(days);

        if(now.isBefore(limitDate)){
            //intime

            return BooleanUtils.isTrue(model.getRead())?FeedbackStatusEnum.READ: FeedbackStatusEnum.NOT_READ;

        }else{
            //pastdue
            return BooleanUtils.isTrue(model.getRead())?FeedbackStatusEnum.READ_PASTDUE: FeedbackStatusEnum.PASTDUE;
        }


    }

    @Override
    public void set(CustomerFeedbackModel model, FeedbackStatusEnum feedbackStatusEnum) {
        throw new UnsupportedOperationException("Write is not a valid operation for this dynamic attribute");
    }

    public ConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    public TimeService getTimeService() {
        return timeService;
    }

    public void setTimeService(TimeService timeService) {
        this.timeService = timeService;
    }


}
