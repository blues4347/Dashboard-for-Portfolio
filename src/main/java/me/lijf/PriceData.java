package me.lijf;

public class PriceData {
    private String name;
    private String latest;
    private String hi;
    private String low;
    private String stamp;

    public PriceData(String name, String latest, String hi, String low, String stamp) {
        this.name = name;
        this.latest = latest;
        this.hi = hi;
        this.low = low;
        this.stamp = stamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatest() {
        return latest;
    }

    public void setLatest(String latest) {
        this.latest = latest;
    }

    public String getHi() {
        return hi;
    }

    public void setHi(String hi) {
        this.hi = hi;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }
}
