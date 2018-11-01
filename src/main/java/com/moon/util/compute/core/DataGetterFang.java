package com.moon.util.compute.core;

/**
 * 从数据源获取数据
 *
 * @author benshaoye
 */
final class DataGetterFang implements AsGetter {

    final AsValuer valuer;
    IGetter getter;

    DataGetterFang(AsValuer valuer) {
        this.valuer = valuer;
    }

    AsHandler toComplex(AsHandler beforeValuer) {
        return new DataGetterComplex((AsValuer) beforeValuer, this.valuer);
    }

    public IGetter getGetter(Object data, Object innerData) {
        IGetter getter = this.getter;
        if (getter == null || !getter.test(data)) {
            getter = IGetter.reset(data, innerData);
            this.getter = getter;
        }
        return getter;
    }

    @Override
    public Object use(Object data) {
        Object innerData = valuer.use(data);
        Object result = getGetter(data, innerData).apply(data, innerData);
        return result;
    }

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param o the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    @Override
    public boolean test(Object o) {
        return false;
    }
}
