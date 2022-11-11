package com.powernode.utils;

import cn.hutool.crypto.SecureUtil;

/*加密*/
public final class MD5Util {

    /*不对外开放*/
    private static String init(String txt){
        if(txt == null){
            return null;
        }
        String encry = SecureUtil.md5(txt);
        return encry;
    }

    /*加密处理*/
    public static String finalMD5(String txt){
        /*后面加入字符串是再次加密*/
        return init(init(init(init(txt)+"bigbird")+"superbird")+"bigbird");
    }

 /*   public static void main(String[] args) {
        System.out.println(finalMD5("123456"));
        //c2f365c379ea6da2c2e42675fae561ac
    }*/

}
