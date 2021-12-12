package sort;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/11/27 5:10 下午
 */
public class Loan {
    private String id;        //第四优先级，越小越优先
    private Integer ovdDays;  //第一优先级，越大越优先
    private BigDecimal rate;  //第二优先级，越大越优先
    private Date startDate;   //第三优先级，越小越优先
    private BigDecimal amount;

    public Loan(String id, Integer ovdDays, BigDecimal rate, Date startDate, BigDecimal amount) {
        this.id = id;
        this.ovdDays = ovdDays;
        this.rate = rate;
        this.startDate = startDate;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getOvdDays() {
        return ovdDays;
    }

    public void setOvdDays(Integer ovdDays) {
        this.ovdDays = ovdDays;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
