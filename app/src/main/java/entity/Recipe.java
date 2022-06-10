package entity;

public class Recipe {

    String title;
    String cat;
    String author;
    int idRecipe;
    int imgId;
    int starImg;
    int difficulty;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(int idRecipe) {
        this.idRecipe = idRecipe;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getStarImg() {
        return starImg;
    }

    public void setStarImg(int starImg) {
        this.starImg = starImg;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String toString() {
        return "Recipe{" +
                "cat='" + cat + '\'' +
                ", title='" + title + '\'' +
                ", imgId=" + imgId +
                ", starImg=" + starImg +
                ", author='" + author + '\'' +
                ']';
    }
}
