package com.ardctraining.core.interceptor;

import com.ardctraining.core.enums.CustomLabelTypeEnum;
import com.ardctraining.core.model.CustomProductLabelModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.user.UserConstants;

import java.util.Objects;

public class CustomProductLabelPrepareInterceptor implements PrepareInterceptor<CustomProductLabelModel> {
    @Override
    public void onPrepare(CustomProductLabelModel customProductLabelModel, final InterceptorContext interceptorContext) throws InterceptorException {
        if (Objects.isNull(customProductLabelModel.getLabelType())){
            customProductLabelModel.setLabelType(CustomLabelTypeEnum.PRIMARY);
        }
        if (Objects.nonNull(customProductLabelModel.getCustomer()) && UserConstants.ANONYMOUS_CUSTOMER_UID.equals(customProductLabelModel.getCustomer().getUid())){
            throw new IllegalCallerException("Unable to save custom label for anonymous customer, change it and trye again");
        }
    }
}
