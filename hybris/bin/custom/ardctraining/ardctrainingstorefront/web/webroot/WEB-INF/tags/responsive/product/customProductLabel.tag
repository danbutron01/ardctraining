<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="customLabels" required="true" type="java.util.List<com.ardctraining.facades.product.data.CustomProductLabelData>" %>

<div class="product-custom-labels">
    <c:forEach items="${customLabels}" var="label">
        <span class="badge">${label.label}</span>
    </c:forEach>
</div>