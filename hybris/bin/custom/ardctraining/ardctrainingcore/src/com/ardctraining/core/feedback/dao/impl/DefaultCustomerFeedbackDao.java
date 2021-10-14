package com.ardctraining.core.feedback.dao.impl;

import com.ardctraining.core.enums.FeedbackStatusEnum;
import com.ardctraining.core.feedback.dao.CustomerFeedbakDao;
import com.ardctraining.core.model.CustomerFeedbackModel;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultCustomerFeedbackDao implements CustomerFeedbakDao {

    private FlexibleSearchService flexibleSearchService;

    private static final Logger LOG = Logger.getLogger(DefaultCustomerFeedbackDao.class);


    private static final String FIND_FEEDBACK_BY_CUSTOMER =
            "SELECT {" + ItemModel.PK + "} " +
                    "FROM   {" + CustomerFeedbackModel._TYPECODE + "} " +
                    "WHERE  {" + CustomerFeedbackModel.CUSTOMER + "} = ?customer ";


    @Override
    public List<CustomerFeedbackModel> findByCustomerAndState(CustomerModel customer) {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(FIND_FEEDBACK_BY_CUSTOMER);
        query.addQueryParameter("customer", customer);

        List<CustomerFeedbackModel> results = findResult(query);
        return results.stream().filter(comment -> isFiltered(comment))
                .collect(Collectors.toList());
    }

    private Boolean isFiltered(CustomerFeedbackModel feedback){
        return (feedback.getStatus() == (FeedbackStatusEnum.NOT_READ) ||
                feedback.getStatus() == (FeedbackStatusEnum.PASTDUE) );
    }

    private List<CustomerFeedbackModel> findResult(final FlexibleSearchQuery query) {
        final SearchResult<CustomerFeedbackModel> result = getFlexibleSearchService().search(query);

        if (Objects.nonNull(result) && CollectionUtils.isNotEmpty(result.getResult())) {
            return result.getResult();
        }

        LOG.warn("unable to find results for query");

        return Collections.emptyList();
    }

    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }
}
