package application.ApiWs.vo;

/**
 * 钱包流水实体类
 */
public class WalletLogVo {
    private String balance;
    private String credit;
    private String debit;
    private String memo;
    private String createdDate;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getDebit() {
        return debit;
    }

    public void setDebit(String debit) {
        this.debit = debit;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "WalletLogVo{" +
                "balance='" + balance + '\'' +
                ", credit='" + credit + '\'' +
                ", debit='" + debit + '\'' +
                ", memo='" + memo + '\'' +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }
}
