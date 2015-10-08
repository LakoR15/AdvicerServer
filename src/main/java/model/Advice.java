package model;

import javax.persistence.*;

@Entity
@Table(name = "advice")
public class Advice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "category")
    private String category;

    @Column(name = "rating")
    private Integer rating;

////    private Boolean fLike;
////
////    private Boolean fDislike;
//
//    public Boolean getfDislike() {
//        return fDislike;
//    }
//
//    public void setfDislike(Boolean fDislike) {
//        this.fDislike = fDislike;
//    }
//
//    public Boolean getfLike() {
//        return fLike;
//    }
//
//    public void setfLike(Boolean fLike) {
//        this.fLike = fLike;
//    }

    public Advice(String text, String category) {
        this.text = text;
        this.category = category;
        this.rating = 0;
//        this.fLike = false;
//        this.fDislike = false;
    }

    public Advice(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Boolean rating) {
        if (rating) {
            this.rating += 1;
        }else {
            this.rating -= 1;
        }
    }
}
