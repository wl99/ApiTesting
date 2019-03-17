public class WeworkConfig {
    public String agentId="1000002";
    public String secret="Qu6ja5nw_qqAJhY3zLqUxEPVbdGD0y0AtwWu8OMwVTA";
    public String corpid = "ww44dc1bfb614f7354";
    public String contactSecret="mgImouSZLWpb6m561bvuI8AWcXPxPJ5oQhxl99eEtSY";

    private static WeworkConfig weworkConfig;
    public static WeworkConfig getInstance(){
        if(weworkConfig==null){
            weworkConfig=new WeworkConfig();
        }
        return weworkConfig;}
}
