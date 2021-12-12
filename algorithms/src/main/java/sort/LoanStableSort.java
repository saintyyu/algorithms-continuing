package sort;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/11/27 5:14 下午
 */
public class LoanStableSort {

    public static void main(String[] args) {
        Date date = new Date();
        Loan loan1 = new Loan("1", 3, new BigDecimal("5"), new Date(date.getTime() + 10000), new BigDecimal("10"));
        Loan loan2 = new Loan("2", 3, new BigDecimal("5"), new Date(date.getTime() + 10000), new BigDecimal("10"));
        Loan loan3 = new Loan("1", 3, new BigDecimal("5"), new Date(date.getTime() + 10000), new BigDecimal("10"));
        Loan loan4 = new Loan("1", 3, new BigDecimal("5"), date, new BigDecimal("10"));
        Loan loan5 = new Loan("1", 3, new BigDecimal("5"), new Date(date.getTime() - 10000), new BigDecimal("10"));
        Loan loan6 = new Loan("1", 5, new BigDecimal("5"), date, new BigDecimal("10"));
        Loan loan7 = new Loan("1", 5, new BigDecimal("7"), date, new BigDecimal("10"));
        Loan loan8 = new Loan("1", 6, new BigDecimal("5"), date, new BigDecimal("10"));
        Loan loan9 = new Loan("1", 6, new BigDecimal("5"), date, new BigDecimal("10"));

        java.util.List<Loan> loans = new ArrayList<>();
        Collections.addAll(loans, loan1, loan2, loan3, loan4,
                loan5, loan6,
                loan7,
                loan8, loan9);
        LoanStableSort.sortLoan(loans); //排序后的结果：8，9，7，6，5，4，1，3，2
        System.out.println();
    }

    public static void sortLoan(List<Loan> list) {
        Collections.sort(list, new Comparator<Loan>() {
            @Override public int compare(Loan o1, Loan o2) {
                int result = LoanStableSort.orderByOvDays(o1, o2);
                if (result < 0) {
                    return 1;
                } else if (result > 0) {
                    return -1;
                } else {
                    result = LoanStableSort.orderByRate(o1, o2);
                    if (result < 0) {
                        return 1;
                    } else if (result > 0) {
                        return -1;
                    } else {
                        result = LoanStableSort.orderByStartTime(o1, o2);
                        if (result != 0) {
                            return result;
                        } else {
                            return LoanStableSort.orderById(o1, o2);
                        }
                    }
                }
            }
        });
    }

    public static int orderByOvDays(Loan loanOne, Loan loanTwo) {
        return loanOne.getOvdDays().compareTo(loanTwo.getOvdDays());
    }

    public static int orderByRate(Loan loanOne, Loan loanTwo) {
        return loanOne.getRate().compareTo(loanTwo.getRate());
    }

    public static int orderByStartTime(Loan loanOne, Loan loanTwo) {
        return loanOne.getStartDate().compareTo(loanTwo.getStartDate());
    }

    public static int orderById(Loan loanOne, Loan loanTwo) {
        return loanOne.getId().compareTo(loanTwo.getId());
    }
}

