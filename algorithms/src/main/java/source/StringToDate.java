package source;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/11/27 4:31 下午
 */
public class StringToDate {
    public static void main(String[] args) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        Date date = dateFormat.parse("2021-10-11 00:00:00");
        System.out.println(date);
    }
}
