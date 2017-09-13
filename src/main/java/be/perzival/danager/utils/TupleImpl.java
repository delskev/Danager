package be.perzival.danager.utils;

import java.util.Optional;

/**
 * Created by Perzival on 06/08/2017.
 */
public class  TupleImpl<L, R> implements Tuple<L, R>{

    private Optional<L> left;
    private Optional<R> right;

    public TupleImpl() {
        left = Optional.empty();
        right = Optional.empty();
    }

    public TupleImpl(L left, R right){
        this.left = Optional.ofNullable(left);
        this.right = Optional.ofNullable(right);
    }


    @Override
    public Optional<L> getLeftValue() {
        return this.left;
    }

    @Override
    public Optional<R> getRightValue() {
        return this.right;
    }

    @Override
    public void setLeftValue(L leftValue) {
        this.left = Optional.of(leftValue);
    }

    @Override
    public void setRightValue(R rightValue) {
        this.right = Optional.of(rightValue);

    }
}
