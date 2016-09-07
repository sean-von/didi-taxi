package com.lianjia.didi.util;

import com.lianjia.didi.dto.DidiTripDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.text.ParseException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fengxiao on 16/9/7.
 */
public class DidiPdfParser {

    /**
     * 匹配一行行程数据
     */
    private static final Pattern pattern = Pattern.compile("[0-9]{1,2}+\\s快车\\s([0-9\\-]+)\\s([0-9:]+)\\s[^\\s]+\\s" +
            "[^\\s]+\\s([^\\s]+)\\s([^\\s]+)\\s[0-9\\.]+\\s([0-9\\.]+).*");

    public static void main(String[] args) throws IOException, ParseException {
        InputStream inputStream = new BufferedInputStream(new FileInputStream(new File("didi.pdf")));
        System.out.println(parse(getContent(inputStream)));
    }

    public static String getContent(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return StringUtils.EMPTY;
        }
        PDDocument pdfDocument = PDDocument.load(inputStream);

        StringWriter writer = new StringWriter();
        PDFTextStripper stripper = new PDFTextStripper();
        stripper.writeText(pdfDocument, writer);
        return writer.getBuffer().toString();
    }

    /**
     * 解析 pdf 文本
     */
    public static List<DidiTripDTO> parse(String content) throws ParseException {
        List<DidiTripDTO> list = new LinkedList<>();
        if (StringUtils.isBlank(content)) {
            return Collections.EMPTY_LIST;
        }

        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            DidiTripDTO dto = new DidiTripDTO();
            dto.setDate(matcher.group(1));
            dto.setTripDuration(matcher.group(2));
            dto.setStartPostion(matcher.group(3));
            dto.setEndPostion(matcher.group(4));
            dto.setCost(matcher.group(5));
            list.add(dto);
        }

        return list;
    }

}
