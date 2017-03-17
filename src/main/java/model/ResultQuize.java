package model;

import javax.persistence.Embeddable;

/**
 * Created by Professional on 2017-03-16.
 */
@Embeddable
public class ResultQuize {
    private Integer quizeResult = 0;
    private Integer possibleMaxResult = 0;

    public Integer getPossibleMaxResult() {
        return possibleMaxResult;
    }

    public ResultQuize setPossibleMaxResult(Integer possibleMaxResult) {
        this.possibleMaxResult = possibleMaxResult;
        return this;
    }

    public Integer getQuizeResult() {
        return quizeResult;
    }

    public ResultQuize setQuizeResult(Integer quizeResult) {
        this.quizeResult = quizeResult;
        return this;
    }

    public void addPoint() {
        quizeResult++;
    }

    public void addCountMaxresult() {
        possibleMaxResult++;
    }


}
