package com.lianjia.didi.service;

import com.lianjia.didi.common.Constant;
import com.lianjia.didi.dto.DidiTripDTO;
import com.lianjia.didi.util.DidiPdfParser;
import com.lianjia.iprd.util.common.SheetBean;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

/**
 * Created by fengxiao on 16/9/7.
 */
@Service
public class PdfService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 转换滴滴行程单为格式化数据
     */
    public SheetBean<DidiTripDTO> getDidiTripList(InputStream inputStream, String name, String costTime) throws IOException,
            ParseException {
        List<DidiTripDTO> list = DidiPdfParser.parse(DidiPdfParser.getContent(inputStream));

        BigDecimal totalCount = new BigDecimal(NumberUtils.DOUBLE_ZERO);
        for (DidiTripDTO didiTripDTO : list) {
            didiTripDTO.setTicketHolder(name);

            /**
             * 格式化起止时间
             */
            if (StringUtils.isNotBlank(didiTripDTO.getTripDuration()) && didiTripDTO.getTripDuration().
                    matches(Constant.DIDI_TIME_PATTERN)) {
                String endTime = DateFormatUtils.format(DateUtils.addMinutes(DateUtils.parseDate(didiTripDTO.
                                getTripDuration(), Constant.DIDI_TIME_FORMAT), Integer.valueOf(costTime)),
                        Constant.DIDI_TIME_FORMAT);
                didiTripDTO.setTripDuration(didiTripDTO.getTripDuration() + "-" + endTime);
            }

            if (StringUtils.isNotBlank(didiTripDTO.getCost()) && didiTripDTO.getCost().matches(Constant.
                    DIDI_COST_PATTERN)) {
                totalCount = totalCount.add(new BigDecimal(Double.toString(Double.parseDouble(didiTripDTO.getCost()))));
            }
        }

        logger.info("报销人[{}]共报销金额 ¥{}", name, totalCount.doubleValue());

        if (list.size() > NumberUtils.INTEGER_ZERO) {
            list.get(NumberUtils.INTEGER_ZERO).setInvoiceNumber(String.valueOf(NumberUtils.INTEGER_ONE));
        }
        return new SheetBean<>(DidiTripDTO.class, list);
    }
}
