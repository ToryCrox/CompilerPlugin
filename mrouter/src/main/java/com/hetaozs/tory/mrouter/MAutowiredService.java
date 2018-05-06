package com.hetaozs.tory.mrouter;

import android.util.LruCache;

import com.hetaozs.tory.mrouter.template.ISyringe;

import java.util.ArrayList;
import java.util.List;

public class MAutowiredService {
    public static final String SUFFIX_AUTOWIRED = "$$MRouter$$Autowired";

    private LruCache<String, ISyringe> classCache;
    private List<String> blackList;

    public MAutowiredService(){
        classCache = new LruCache<>(66);
        blackList = new ArrayList<>();
    }

    public void inject(Object instance){
        String className = instance.getClass().getName();
        if(blackList.contains(className)){
            return;
        }
        try {
            ISyringe autowiredHelper = classCache.get(className);
            if (null == autowiredHelper) {  // No cache.
                autowiredHelper = (ISyringe) Class.forName(instance.getClass().getName() + SUFFIX_AUTOWIRED).getConstructor().newInstance();
            }
            autowiredHelper.inject(instance);
            classCache.put(className, autowiredHelper);
        } catch (Exception e) {
            blackList.add(className);
        }
    }
}
