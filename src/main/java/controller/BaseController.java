package controller;

import utils.EMF;

public abstract class BaseController extends EMF {

    public BaseController(){
        routes();
    }

    abstract public void routes();
}
