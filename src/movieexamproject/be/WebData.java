/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieexamproject.be;

/**
 *
 * @author domin
 */
public class WebData {
    private int movieId;
    private int imdbRating;
    private String imdbLink;

    public WebData(int movieId, int imdbRating, String imdbLink) {
        this.movieId = movieId;
        this.imdbRating = imdbRating;
        this.imdbLink = imdbLink;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(int imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbLink() {
        return imdbLink;
    }

    public void setImdbLink(String imdbLink) {
        this.imdbLink = imdbLink;
    }
}
