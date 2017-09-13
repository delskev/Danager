package be.perzival.danager.utils;

import java.util.Optional;

/**
 * Created by Perzival on 06/08/2017.
 */
public interface Tuple<L, R> {

    Optional<L> getLeftValue();
    Optional<R> getRightValue();

    void setLeftValue(L leftValue);
    void setRightValue(R rightValue);
}
