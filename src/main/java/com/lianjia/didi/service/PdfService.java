package com.lianjia.didi.service;

import com.lianjia.didi.common.Constant;
import com.lianjia.didi.dto.DidiTripDTO;
import com.lianjia.didi.util.DidiPdfParser;
import com.lianjia.iprd.util.common.SheetBean;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

/**
 * Created by fengxiao on 16/9/7.
 */
@Service
public class PdfService {

    public SheetBean<DidiTripDTO> getDidiTripList(InputStream inputStream, String name, String costTime) throws IOException,
            ParseException {
        List<DidiTripDTO> list = DidiPdfParser.parse(DidiPdfParser.getContent(inputStream));
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
        }

        if (list.size() > NumberUtils.INTEGER_ZERO) {
            list.get(NumberUtils.INTEGER_ZERO).setInvoiceNumber(String.valueOf(NumberUtils.INTEGER_ONE));
        }
        return new SheetBean<>(DidiTripDTO.class, list);
    }
}
