package com.ehcache;

import com.utils.EhcacheUtil;
import net.sf.ehcache.Cache;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : huang
 * @date :  2021/2/4
 * @time : 14:45
 * To change this template use File | Settings | File and Code Templates.
 */
public class Main111 {
    public static void main(String[] args) {
        EhcacheUtil.getInstance().put("ameyaProductInfoCacheRepertory","key","value");
        System.out.println(EhcacheUtil.getInstance().get("ameyaProductInfoCacheRepertory","key"));

    }
}
