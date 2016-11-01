        package br.com.bearsoft.watsonservicetest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tone implements Comparable<Tone>{


    public Tone(String toneName, String toneId, int icone) {
        this.toneName = toneName;
        this.toneId = toneId;
        this.icone = icone;
    }

    private int icone;

    @SerializedName("tone_id")
    @Expose
    private String toneId;
    @SerializedName("tone_name")
    @Expose
    private String toneName;
    @SerializedName("score")
    @Expose
    private Double score;

    /**
     *
     * @return
     * The toneId
     */
    public String getToneId() {
        return toneId;
    }

    /**
     *
     * @param toneId
     * The tone_id
     */
    public void setToneId(String toneId) {
        this.toneId = toneId;
    }

    /**
     *
     * @return
     * The toneName
     */
    public String getToneName() {
        return toneName;
    }

    /**
     *
     * @param toneName
     * The tone_name
     */
    public void setToneName(String toneName) {
        this.toneName = toneName;
    }

    /**
     *
     * @return
     * The score
     */
    public Double getScore() {
        return score;
    }

    /**
     *
     * @param score
     * The score
     */
    public void setScore(Double score) {
        this.score = score;
    }


    @Override
    public String toString() {
        return this.getToneName() + "/" + this.getScore();
    }

    public int getIcone() {
        return icone;
    }

    public void setIcone(int icone) {
        this.icone = icone;
    }

    @Override
    public int compareTo(Tone o) {

        //Descending order
        if (this.getScore() - o.getScore() > 0)
            return -1;
        else if (this.getScore() - o.getScore() < 0)
            return 1;
        else
            return 0;
    }
}