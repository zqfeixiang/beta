package com.dong.beta.utils;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

public class BondCodeUtil {

    private static List<String> convertList = Arrays.asList("bondCode", "securidMain", "bondCodeList");

    private static final String BOND_SEPARATOR = ",";
    private static final String WD_SH = ".SH";
    private static final String WD_SZ = ".SZ";
    private static final String WD_IB = ".IB";
    private static final String GB_SH = ".XSHG";
    private static final String GB_SZ = ".XSHE";
    private static final String GB_IB = ".XCIB";

    private static final String CREDIT = "credit";

    public static boolean isNeedToConvert(String property, HttpServletRequest request){
        String referer = request.getHeader("referer");
        return isBondColumn(property) && !StringUtils.isEmpty(referer) && CREDIT.equals(referer);
    }

    public static boolean isBondColumn(String property){
        for (String str : convertList){
            if (str.equalsIgnoreCase(property)){
                return true;
            }
        }
        return false;
    }

    /**
     * 万得码转为国标码 处理含 ',' 的情况
     * @param bondCode
     * @return
     */
    public static String convertWdToGbs(String bondCode){
        if (StringUtils.isEmpty(bondCode)) return null;
        if (bondCode.contains(BOND_SEPARATOR)){
            String[] bondCodes = bondCode.split(BOND_SEPARATOR);
            StringBuilder builder = new StringBuilder();
            for (String bond  : bondCodes){
                builder.append(convertWdToGb(bondCode)).append(",");
            }
            return builder.substring(0, builder.lastIndexOf(BOND_SEPARATOR));
        }
        return convertWdToGb(bondCode);
    }

    /**
     * 万得码转为国标码
     * 100000.IB -> B10000.XCIB
     * 100000.SZ -> 10000.XSHE
     * 100000.SH -> 10000.XSHG
     *
     * @param bondCode
     * @return
     */
    public static String convertWdToGb(String bondCode){
        String result = null;
        if (StringUtils.isEmpty(bondCode)) return result;

        if (bondCode.contains(WD_SH)){
            result = bondCode.replace(WD_SH, GB_SH);
        }else if (bondCode.contains(WD_SZ)){
            result = bondCode.replace(WD_SZ, GB_SZ);
        }else if (bondCode.contains(WD_IB)){
            result = "B" + bondCode.replace(WD_IB, GB_IB);
        }else {
            return result;
        }
        return result;
    }

    /**
     * 国标码转为万得码
     * B100000.XCIB -> 10000.IB
     * 100000.XSHE -> 10000.SZ
     * 100000.XSHG -> 10000.SH
     * @param bondCode
     * @return
     */
    public static final String convertGbToWd(String bondCode){
        String result = null;
        if (StringUtils.isEmpty(bondCode)) return result;

        if (bondCode.contains(GB_SH)){
            result = bondCode.replace(GB_SH, WD_SH);
        }else if (bondCode.contains(GB_SZ)){
            result = bondCode.replace(GB_SZ, WD_SH);
        }else if (bondCode.contains(GB_IB)){
            result = bondCode.substring(1).replace(GB_IB, WD_IB);
        }else {
            return result;
        }
        return result;
    }

    /**
     * 万得码转为国标码 List类型
     * @param bondCodeList
     * @return
     */
    public static List<String> convertWdToGbList(List<String> bondCodeList) {
        List<String> result = Lists.newArrayList();
        bondCodeList.stream().forEach(bondCode -> result.add(convertWdToGb(bondCode)));
        return result;
    }

    /**
     * 国标码转为万得码 List类型
     * @param bondCodeList
     * @return
     */
    public static List<String> convertGbToWdList(List<String> bondCodeList) {
        List<String> result = Lists.newArrayList();
        bondCodeList.stream().forEach(bondCode -> result.add(convertGbToWd(bondCode)));
        return result;
    }
}
