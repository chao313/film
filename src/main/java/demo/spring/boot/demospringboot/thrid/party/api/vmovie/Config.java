package demo.spring.boot.demospringboot.thrid.party.api.vmovie;

public class Config {
    private static String VMOVIE_URL = "https://www.vmovier.com/post/getbytab";
    public static String VMOVIE_BAE_URL = "https://www.vmovier.com";

    public enum Tab {
        HOT("hot"), NEW("new"), STARTS("starts"), RAND("rand");
        private String tab;

        Tab(String tab) {
            this.tab = tab;
        }

        public String getTab() {
            return "?tab=" + this.tab;
        }

        public String getTabValue() {
            return this.tab;
        }
    }

    public static String generateUrl(Config.Tab tab, Integer page, Integer pagepart) {
        return VMOVIE_URL + tab.getTab() + "&" + "page=" + page + "&" + "pagepart=" + pagepart;
    }


//    String HOT_VMOVIE_URL = "https://www.vmovier.com/post/getbytab?tab=hot&page=1&pagepart=1";
//
//    //page 是第几页 pagepart是加载第几次
//    String NEW_VMOVIE_URL = "https://www.vmovier.com/post/getbytab?tab=new&page=4&pagepart=2";
//    String STARS_VMOVIE_URL = "https://www.vmovier.com/post/getbytab?tab=stars&page=1&pagepart=2";
//    String RAND_VMOVIE_HEAD = "https://www.vmovier.com/post/getbytab?tab=rand&page=1&pagepart=2";
}
