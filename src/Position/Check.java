package Position;
 
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
 
//java�����signatureǩ��
public class Check {
        /*public static void main(String[] args) throws UnsupportedEncodingException,
                        NoSuchAlgorithmException {
        		test snCal = new test();
 
               // ����sn�������Գ���˳���йأ�get������ʹ��LinkedHashMap����<key,value>���÷�������key�Ĳ���˳������post��ʹ��TreeMap����<key,value>���÷������Զ���key������ĸa-z˳����������get������Զ������˳��sn������������󣩷������󣬵���post������밴����ĸa-z˳�����body��sn������������󣩡���get����Ϊ����http://api.map.baidu.com/geocoder/v2/?address=�ٶȴ���&output=json&ak=yourak��paramsMap���ȷ���address���ٷ�output��Ȼ���ak������˳������get�����ж�Ӧ�����ĳ���˳�򱣳�һ�¡�
                Map paramsMap = new LinkedHashMap<String, String>();
                paramsMap.put("address", "�ٶȴ���");
                paramsMap.put("output", "json");
                paramsMap.put("ak", "yourak");
 
                // ���������toQueryString��������LinkedHashMap������value��utf8���룬ƴ�ӷ��ؽ��address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourak
                String paramsStr = snCal.toQueryString(paramsMap);
 
                // ��paramsStrǰ��ƴ����/geocoder/v2/?������ֱ��ƴ��yoursk�õ�/geocoder/v2/?address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourakyoursk
                String wholeStr = new String("/geocoder/v2/?" + paramsStr + "yoursk");
 
                // ������wholeStr����utf8����
                String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
 
                // ���������MD5�����õ�����snǩ��7de5a22212ffaa9e326444c75a58f9a0
                System.out.println(snCal.MD5(tempStr));
        }*/
 
        // ��Map������value��utf8���룬ƴ�ӷ��ؽ��
        public String toQueryString(Map<?, ?> data)
                        throws UnsupportedEncodingException {
                StringBuffer queryString = new StringBuffer();
                for (Entry<?, ?> pair : data.entrySet()) {
                        queryString.append(pair.getKey() + "=");
                        queryString.append(URLEncoder.encode((String) pair.getValue(),
                                        "UTF-8") + "&");
                }
                if (queryString.length() > 0) {
                        queryString.deleteCharAt(queryString.length() - 1);
                }
                return queryString.toString();
        }
 
        // ����stackoverflow��MD5���㷽����������MessageDigest�⺯��������byte������ת����16����
        public String MD5(String md5) {
                try {
                        java.security.MessageDigest md = java.security.MessageDigest
                                        .getInstance("MD5");
                        byte[] array = md.digest(md5.getBytes());
                        StringBuffer sb = new StringBuffer();
                        for (int i = 0; i < array.length; ++i) {
                                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                                                .substring(1, 3));
                        }
                        return sb.toString();
                } catch (java.security.NoSuchAlgorithmException e) {
                }
                return null;
        }
}