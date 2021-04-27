package test.JSONModel;

public class Props {


    private String correlationID;

    private String currency;

    private boolean showInterfaceViewToggle;

    private String  pageProps;



    public void setPageProps(String pageProps) {
        this.pageProps = pageProps;
    }

    public String getPageProps() {
        return pageProps;
    }

    public String getCorrelationID(){
        return this.correlationID;
    }
    public void setCurrency(String currency){
        this.currency = currency;
    }
    public String getCurrency(){
        return this.currency;
    }

    public void setShowInterfaceViewToggle(boolean showInterfaceViewToggle){
        this.showInterfaceViewToggle = showInterfaceViewToggle;
    }
    public boolean getShowInterfaceViewToggle(){
        return this.showInterfaceViewToggle;
    }


}
