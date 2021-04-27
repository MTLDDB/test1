package test.model;

/**
 * @author: Ant
 * @Date: 2018/11/10 16:04
 * @Description:
 */
public class SpiderTask {
    // 任务ID
    private String id;
    // 任务归属网站
    private String domian;
    // 任务类型，列表任务OR详情任务 0 - list ,  1 - detail
    private Integer type;
    // 任务URL
    private String url;
    //请求方式 post OR get
    private String requestWay;
    // 任务优先级
    private int priority = 0;
    // 是否为库存更新任务，如果是则在列表页只会爬取库存信息，不返回页面的urllist 0-否 1-是
    private int stockUpdate;
    // 是否为filter更新任务，如果是则在列表页只会爬取filter信息，不返回页面的urllist 0-否 1-是
    private int filterUpdate;

    private String cookie;//cookie 多个参数,逗号分隔
    private String referer;// referer参数
    private String params;    //爬虫参数   多个参数,逗号分隔

    // 任务被执行次数，因为执行成功的不会再被执行，所以也可以看作是失败次数
    private int failNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDomian() {
        return domian;
    }

    public void setDomian(String domian) {
        this.domian = domian;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestWay() {
        return requestWay;
    }

    public void setRequestWay(String requestWay) {
        this.requestWay = requestWay;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public int getFailNum() {
        return failNum;
    }

    public void setFailNum(int failNum) {
        this.failNum = failNum;
    }

    public int getStockUpdate() {
        return stockUpdate;
    }

    public void setStockUpdate(int stockUpdate) {
        this.stockUpdate = stockUpdate;
    }

    public int getFilterUpdate() {
        return filterUpdate;
    }

    public void setFilterUpdate(int filterUpdate) {
        this.filterUpdate = filterUpdate;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    @Override
    public String toString() {
        return "SpiderTask{" +
                "id='" + id + '\'' +
                ", domian='" + domian + '\'' +
                ", type=" + type +
                ", url='" + url + '\'' +
                ", requestWay='" + requestWay + '\'' +
                ", priority=" + priority +
                ", stockUpdate=" + stockUpdate +
                ", filterUpdate=" + filterUpdate +
                ", cookie='" + cookie + '\'' +
                ", referer='" + referer + '\'' +
                ", params='" + params + '\'' +
                ", failNum=" + failNum +
                '}';
    }
}
