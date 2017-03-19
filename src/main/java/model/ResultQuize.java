package model;

import javax.persistence.Embeddable;

/**
 * Created by Professional on 2017-03-16.
 */
@Embeddable
public class ResultQuize {
    private Integer quizeResult = 0;
    private Integer possibleMaxResult = 0;

    public Integer getQuizeResult() {
        return quizeResult;
    }

    public void addPoint() {
        quizeResult++;
    }

    public void addCountMaxresult() {
        possibleMaxResult++;
    }


}
