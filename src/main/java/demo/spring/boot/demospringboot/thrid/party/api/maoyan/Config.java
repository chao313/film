package demo.spring.boot.demospringboot.thrid.party.api.maoyan;

public interface Config {
    String HOT_MOVIE_URL = "http://m.maoyan.com/movie/list.json?type=hot&offset=0&limit=1000";
    //获取movie的细节
    String MOVIE_DETAIL_HEAD = "http://m.maoyan.com/movie/";
    String MOVIE_DETAIL_END = ".json";

    String COMMENT_URL = "http://m.maoyan.com/comments.json";

    String CINEMAS_URL = "http://m.maoyan.com/cinemas.json";

    //http://m.maoyan.com/showtime/wrap.json?cinemaid=9271&movieid=341138
    String CINEMAS_DETAILS = "http://m.maoyan.com/ajax/cinemaDetail";
    String SEATS = "http://m.maoyan.com/show/seats";
    //新的接口 http://m.maoyan.com/ajax/seatingPlan?seqNo=201804150149616
    String NEW_SEATS = "http://m.maoyan.com/ajax/seatingPlan";

}
