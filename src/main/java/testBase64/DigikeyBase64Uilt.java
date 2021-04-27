package testBase64;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Float.*;

class DigikeyBase64Uilt {
    public static String _keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
    static String _f = "String.fromCharCode";

//    public static void main(String[] args) {
//        for(int i=1;i<=30;i++){
//            System.out.println(DigikeyBase64Uilt.compressToBase64(100,i) +"---"+i);
//        }
//        //N4IgrCBcoA5QLAGhDOkBMYC%2BWg
//        //N4IgrCBcoA5QLAGhDOkBMYC+WgA
//
//        //System.out.println(compress("{\"5\":{\"p\":1,\"pp\":100}}"));
//        //System.out.println(compress("huang"));
//    }
    private static String g1(String e) {
        StringBuffer sb = new StringBuffer(e);
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < sb.length(); i++) {
            // 取出每一个字符
            char c = sb.charAt(i);
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }
        return unicode.toString();
    }

    private static String g(int e) {
        char re = (char) e;
        return String.valueOf(re);
    }

    /**
     *  Base64
     * @param pageSize 分页大小
     * @param pageNum 分页编号
     * @return
     */
    public static String compressToBase64(int pageSize,int pageNum) {
        String e="{\"5\":{\"p\":"+pageNum+",\"pp\":"+pageSize+"}}";
        if (null == e) return "";
        Integer i;
        float n;
        float r;
        Integer o;
        Integer s;
        Integer a;
        Integer c;
        String u = "";
        e = compress(e);
        int p = 0;
        StringBuffer sb = new StringBuffer(e);
        while ( p < 2 * sb.length()) {
            if (p % 2 == 0) {
                i = sb.charAt(p / 2) >> 8;
                n = 255 & sb.charAt(p / 2);
                if(p/2 + 1 < e.length()){
                    r=sb.charAt(p/2 + 1)>>8 ;
                }else {
                    r=NaN;
                }

            } else {
                i = 255 & sb.charAt((p - 1) / 2);
                if ((p + 1) / 2 < e.length()) {
                    n = sb.charAt((p + 1) / 2) >> 8;
                    r = 255 & sb.charAt((p + 1) / 2);
                } else {
                    n = NaN;
                    r = NaN;
                }
            }
            p += 3;
            o = i >> 2;
            s = (3 & i) << 4 | (int)n >> 4;
            a = (15 & (int)n) << 2 | (int)r >> 6;
            c = 63 & (int)r;
            if (isNaN(n)) {
                a = c = 64;
            } else {
                if (isNaN(r)) {
                    c = 64;
                }
            }
            u = u + DigikeyBase64Uilt._keyStr.charAt(o) + DigikeyBase64Uilt._keyStr.charAt(s) + DigikeyBase64Uilt._keyStr.charAt(a) + DigikeyBase64Uilt._keyStr.charAt(c);
        }
        return u.replace("==","").replace("=","");
        //return u;
    }

    private static String compress(String e) {
        StringBuffer sb = new StringBuffer(e);
        if (null == e) return "";
        Map<String, Integer> o= new HashMap<>();
        Map<String, Boolean> s = new HashMap<>();
        String a="" ;
        String u = "";
        String c = "";
        String f = "";
        int p = 2, h = 3, l = 2, d = 0, v = 0, r;
        for (r = 0; r < e.length(); r++) {
            a = String.valueOf(sb.charAt(r));
            if (!o.containsKey(a)) {
                o.put(a, h++);
                s.put(a, true);
            }
            c = u + a;
            if (o.containsKey(c)) {
                u = c;
            } else {
                if (s.containsKey(u)) {
                    StringBuffer ub = new StringBuffer(u);
                    if ((int)ub.charAt(0) < 256) {
                        for (int i = 0; i < l; i++) {
                            d <<= 1;
                            if (15 == v) {
                                v = 0;
                                f += g(d);
                                d = 0;
                            } else {
                                v++;
                            }
                        }
                        for (int n = ub.charAt(0), i = 0; i < 8; i++) {
                            d = d << 1 | 1 & n;
                            if (15 == v) {
                                v = 0;
                                f += g(d);
                                d = 0;
                            } else {
                                v++;
                            }
                            n >>= 1;
                        }
                    } else {
                        for (int n = 1, i = 0; i < l; i++) {
                            d = d << 1 | n;
                            if (15 == v) {
                                v = 0;
                                f += g(d);
                                d = 0;
                            } else {
                                v++;
                            }
                            n = 0;
                        }
                        for (int n = ub.charAt(0), i = 0; i < 16; i++) {
                            d = d << 1 | 1 & n;
                            if (15 == v) {
                                v = 0;
                                f += g(d);
                                d = 0;
                            } else {
                                v++;
                            }
                            n >>= 1;
                        }
                    }
                    if (0 == --p) {
                        p = (int) Math.pow(2, l);
                        l++;
                    }
                    s.remove(u);
                } else {
                    for (int n = o.get(u), i = 0; i < l; i++) {
                        d = d << 1 | 1 & n;
                        if (15 == v) {
                            v = 0;
                            f += g(d);
                            d = 0;
                        } else {
                            v++;
                        }
                        n >>= 1;
                    }
                }
                if (0 == --p) {
                    p = (int) Math.pow(2, l);
                    l++;
                }
                o.put(c, h++);
                u = a;
            }

        }
        if (!"".equals(u)) {
            StringBuffer ub = new StringBuffer(u);
            if (s.containsKey(u)) {
                if (ub.charAt(0) < 256) {
                    for (int i = 0; i < l; i++) {
                        d <<= 1;
                        if (15 == v) {
                            v = 0;
                            f += g(d);
                            d = 0;
                        } else {
                            v++;
                        }
                    }
                    for (int n = ub.charAt(0), i = 0; i < 8; i++) {
                        d = d << 1 | 1 & n;
                        if (15 == v) {
                            v = 0;
                            f += g(d);
                            d = 0;
                        } else {
                            v++;
                        }
                        n >>= 1;
                    }
                } else {
                    for (int n = 1, i = 0; i < l; i++) {
                        d = d << 1 | n;
                        if (15 == v) {
                            v = 0;
                            f += g(d);
                            d = 0;
                        } else {
                            v++;
                        }
                        n = 0;

                    }
                    for (int n = ub.charAt(0), i = 0; i < 16; i++) {
                        d = d << 1 | 1 & n;
                        if (15 == v) {
                            v = 0;
                            f += g(d);
                            d = 0;
                        } else {
                            v++;
                        }
                        n >>= 1;
                    }
                }
                if (0 == --p) {
                    p = (int) Math.pow(2, l);
                    l++;
                }
                s.remove(u);
            } else {
                for (int n = o.get(u), i = 0; i < l; i++) {
                    d = d << 1 | 1 & n;
                    if (15 == v) {
                        v = 0;
                        f += g(d);
                        d = 0;
                    } else {
                        v++;
                    }
                    n >>= 1;
                }
            }
            if (0 == --p) {
                p = (int) Math.pow(2, l);
                l++;
            }
        }
        for (int n = 2, i = 0; i < l; i++) {
            d = d << 1 | 1 & n;
            if (15 == v) {
                v = 0;
                f += g(d);
                d = 0;
            } else {
                v++;
            }
            n >>= 1;
        }
        while (true ) {
            d <<= 1;
            if (15 == v) {
                f += g(d);
                break;
            }
            v++;
        }
        return f;
    }
}

