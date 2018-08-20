package dte.dao.pagination.provider;

import java.util.Map;

import dte.dao.pagination.model.PageRequest;
import dte.dao.pagination.model.PageTypedResponse;

/**
 * Created by ElZu on 03.04.2018.
 * Page provider for server-side pagination in datatables
 */
public interface PageProvider<T, TView> {

    /*List<T> getPage(String orderColumn, boolean asc, int start, int length, String searchStr, Map<String, Object> additionalParams);
    List<T> getPage(AjaxRequest ajaxRequest, Map<String, Object> additionalParams);
    Long getTotalCountFiltered(String searchStr, Map<String, Object> additionalParams);
    Long getTotalCountFiltered(AjaxRequest ajaxRequest, Map<String, Object> additionalParams);
    Long getTotalCount(Map<String, Object> additionalParams);*/

    PageTypedResponse<TView> getPageResponseView(PageRequest ajaxRequest, Map<String, Object> additionalParams) throws Exception;
    PageTypedResponse<T> getPageResponse(PageRequest ajaxRequest, Map<String, Object> additionalParams);
}
