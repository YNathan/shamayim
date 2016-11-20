package Entity;

/**
 * Created by jacky on 18/11/2016.
 */
public class WebResponce {
    private ESuccessFailed eSuccessFailed;
    private String szReason;

    public WebResponce() {
        this.eSuccessFailed = ESuccessFailed.SUCCESS;
        this.szReason = "Operation Success";
    }

    public ESuccessFailed getSuccessFailed() {
        return eSuccessFailed;
    }

    public void seteSuccessFailed(ESuccessFailed eSuccessFailed) {
        this.eSuccessFailed = eSuccessFailed;
    }

    public String getReason() {
        return szReason;
    }

    public void setReason(String szReason) {
        this.szReason = szReason;
    }

    @Override
    public String toString() {
        return "WebResponce{" +
                "eSuccessFailed=" + eSuccessFailed +
                ", szReason='" + szReason + '\'' +
                '}';
    }

    public String toJson() {
        return "WebResponce{" +
                "eSuccessFailed=" + eSuccessFailed +
                ", szReason='" + szReason + '\'' +
                '}';
    }
}
