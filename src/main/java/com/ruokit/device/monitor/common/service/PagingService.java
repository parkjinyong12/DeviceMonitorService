package com.ruokit.device.monitor.common.service;

import com.ruokit.device.monitor.model.data.PageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class PagingService {

    private static Logger logger = LoggerFactory.getLogger(PagingService.class);

    public PageView getPaging(Page<?> deviceDetailPage) {

        PageView pageView = new PageView();
        // records - totalCnt
        pageView.setRecords(String.valueOf(deviceDetailPage.getTotalElements()));
        // rows - data
        pageView.setRows(deviceDetailPage.getContent());
        // page - current page(화면 page index는 1부터 시작)
        pageView.setPage(String.valueOf(deviceDetailPage.getPageable().getPageNumber()+1));
        // total - total page
        pageView.setTotal(String.valueOf(deviceDetailPage.getTotalPages()));

        return pageView;
    }

    public PageRequest getPageRequest(HttpServletRequest request) {

        Map<String, Integer> pageParamMap = new HashMap<String, Integer>();
        List<String> checkList = Arrays.asList(new String[] {"page", "rows"});
        try {
            checkList.forEach( checkValue -> {
                pageParamMap.put(checkValue, Integer.valueOf(request.getParameter(checkValue)));
            });
        } catch (NumberFormatException e) {
            logger.info("PageRequest Param 형 변환 에러. String -> Integer");
        }

        Integer page = pageParamMap.get("page");
        Integer rows = pageParamMap.get("rows");

        // jqgrid index는 1부터 시작하나, pageable index는 0부터 시작.
        if(page > 0) {
            page = page - 1;
        } else {
            throw new NumberFormatException("PageRequest Param size 에러. page index는 1보다 작으면 안됨.");
        }
        return PageRequest.of(page, rows);
    }
}
