package com.ardctraining.backoffice.feedback;
import com.ardctraining.core.model.CustomerFeedbackModel;
import com.hybris.cockpitng.labels.LabelProvider;import java.util.Objects;public class CustomerFeedbackLabel implements LabelProvider<CustomerFeedbackModel> { private static final String ALL_WILDCARD = "*"; @Override
public String getLabel(CustomerFeedbackModel customerFeedbackModel) { return new StringBuilder()
        .append(customerFeedbackModel.getSubject())
        .append(" - ")
        .append(customerFeedbackModel.getCustomer().getUid())
        .toString();
} @Override
public String getDescription(CustomerFeedbackModel customerFeedbackModel) {
    return getLabel(customerFeedbackModel);
} @Override
public String getIconPath(CustomerFeedbackModel customerFeedbackModel) {
    return null;
}
}

