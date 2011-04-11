package com.combanc.commonsupport.dataaccess.Abstract;

import com.combanc.commonsupport.dataaccess.factory.IDataAccessFacade;

public abstract class AbstractDataAccessFacade implements IDataAccessFacade {

    //////////////////////////////////  统计图  ////////////////////////////////////
    public AbstractChart abstractChart;

    public AbstractChart getAbstractChart() {
        return abstractChart;
    }
    
    // pda
    public AbstractRequestAssets abstractRequestAssets;

    public AbstractRequestAssets getAbstractRequestAssets() {
        return abstractRequestAssets;
    }
}
