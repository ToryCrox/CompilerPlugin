package com.hetaozs.tory.mrouter;

public class MRouter {

    MAutowiredService autowiredService;

    private static class MRouterHolder{
        private static MRouter instance = new MRouter();
    }

    public static MRouter getInstance(){
        return MRouterHolder.instance;
    }


    public MRouter(){

    }

    public void inject(Object instance){
        if(autowiredService != null){
            autowiredService.inject(instance);
        }
    }
}
