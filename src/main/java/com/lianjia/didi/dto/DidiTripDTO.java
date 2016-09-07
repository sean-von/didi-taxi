package com.lianjia.didi.dto;

import com.lianjia.didi.common.Constant;
import com.lianjia.iprd.util.annotation.Cell;
import com.lianjia.iprd.util.annotation.Sheet;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Calendar;

/**
 * Created by fengxiao on 16/9/7.
 */
@Sheet(name = "打车报销单")
public class DidiTripDTO {

    @Cell(name = "日期", column = 1)
    private String date;

    @Cell(name = "起止时间", column = 2)
    private String tripDuration;

    @Cell(name = "事由", column = 3, defaultValue = "加班")
    private String reason;

    @Cell(name = "起点", column = 4)
    private String startPostion;

    @Cell(name = "终点", column = 5)
    private String endPostion;

    @Cell(name = "金额", column = 6)
    private String cost;

    @Cell(name = "票据张数", column = 7, defaultValue = "0")
    private String invoiceNumber;

    @Cell(name = "报销人", column = 8)
    private String ticketHolder;

    @Cell(name = "备注", column = 9, defaultValue = "滴滴快车")
    private String remark;

    public String getDate() {
        return date;
    }

    public void setDate(String date) throws ParseException {
        if (StringUtils.isBlank(date)) {
            this.date = StringUtils.EMPTY;
        }

        this.date = DateFormatUtils.format(DateUtils.setYears(DateUtils.parseDate(date, Constant.DIDI_DATE_FORMAT),
                Calendar.getInstance().get(Calendar.YEAR)), Constant.EXCEL_DATE_FORMAT);
    }

    public String getTripDuration() {
        return tripDuration;
    }

    public void setTripDuration(String tripDuration) {
        if (StringUtils.isBlank(tripDuration)) {
            this.tripDuration = StringUtils.EMPTY;
        }
        this.tripDuration = tripDuration;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStartPostion() {
        return startPostion;
    }

    public void setStartPostion(String startPostion) {
        this.startPostion = startPostion;
    }

    public String getEndPostion() {
        return endPostion;
    }

    public void setEndPostion(String endPostion) {
        this.endPostion = endPostion;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getTicketHolder() {
        return ticketHolder;
    }

    public void setTicketHolder(String ticketHolder) {
        this.ticketHolder = ticketHolder;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "DidiTripDTO{" +
                "date='" + date + '\'' +
                ", tripDuration='" + tripDuration + '\'' +
                ", reason='" + reason + '\'' +
                ", startPostion='" + startPostion + '\'' +
                ", endPostion='" + endPostion + '\'' +
                ", cost='" + cost + '\'' +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", ticketHolder='" + ticketHolder + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
