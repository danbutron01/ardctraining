package com.ardctraining.core.product.dao.impl;

import com.ardctraining.core.model.CustomProductLabelModel;
import com.ardctraining.core.product.dao.CustomProductLabelDao;
import de.hybris.platform.catalog.model.ItemSyncDescriptorModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.apache.commons.collections.CollectionUtils;
import org.spockframework.util.CollectionUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.apache.log4j.Logger;

public class DefaultCustomProductLabelDao implements CustomProductLabelDao {

    private FlexibleSearchService flexibleSearchService;

    private static final Logger LOG = Logger.getLogger(DefaultCustomProductLabelDao.class);
    private static final String FIND_LABELS_BY_CUSTOMER_AND_PRODUCCT_QUERY =
                    "SELECT ("  +   ItemSyncDescriptorModel.PK + ") " +
                    "FROM   ("  +   CustomProductLabelModel._TYPECODE + ") "+
                    "WHERE  ("  +   CustomProductLabelModel.CUSTOMER  + ") = ?customer AND " +
                    "       ("  +   CustomProductLabelModel.PRODUCT + ") = ?product";

    @Override
    public List<CustomProductLabelModel> findByCustomerAndProduct(CustomerModel customer, ProductModel product) {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(FIND_LABELS_BY_CUSTOMER_AND_PRODUCCT_QUERY);
        query.addQueryParameter("customer",customer);
        query.addQueryParameter("product",product);

        final SearchResult<CustomProductLabelModel> result = getFlexibleSearchService().search(query);

        if(Objects.nonNull(result) && CollectionUtils.isNotEmpty(result.getResult())){
            return result.getResult();
        }

        LOG.warn("unable tp find results for custom product labels");
        return Collections.emptyList();
    }

    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }
}
