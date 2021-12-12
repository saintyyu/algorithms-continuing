package source;

import java.util.Date;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/11/27 4:35 下午
 */
public class DateDuration {
    public static void main(String[] args) throws Exception {
        Date date1 = new Date(System.currentTimeMillis() - 24*60*60*10*000+5000);


        Thread.sleep(1000);

        Date date2 = new Date();



        long duration = date2.getTime() - date1.getTime();
        long range = duration / (24*60*60*1000);
        System.out.println(range);

    }
}
