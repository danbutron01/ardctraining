package com.ardctraining.facades.populators

import com.ardctraining.core.model.CustomProductLabelModel
import com.ardctraining.facades.product.data.CustomProductLabelData
import de.hybris.platform.converters.Populator
import de.hybris.platform.servicelayer.dto.converter.ConversionException

class CustomProductLabelPopulator implements Populator<CustomProductLabelModel, CustomProductLabelData>{
    @Override
    void populate(CustomProductLabelModel source, CustomProductLabelData target) throws ConversionException {
        target.setType(source.getLabelType().getCode());
        target.setLabel(source.getLabel());
    }
}
