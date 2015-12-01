package com.krod.adapter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * 将数据类型跟样式绑定起来
 * Created by jian.wj on 15-11-25.
 */
public class AdapterUtil {

    public static <T> BaseViewHolder addItem(Class<? extends BaseViewHolder> c, T t) {
        try {
            Constructor<? extends BaseViewHolder> constructor = c.getConstructor(t.getClass());
            return constructor.newInstance(new Object[]{t});
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> ArrayList<BaseViewHolder> setList(Class<? extends BaseViewHolder> c, ArrayList<T> data) {
        ArrayList<BaseViewHolder> list = new ArrayList<>();
        try {
            Constructor<? extends BaseViewHolder> constructor = c.getConstructor(data.get(0).getClass());
            int len = data.size();
            for (int i = 0; i < len; i++) {
                list.add(constructor.newInstance(new Object[]{data.get(i)}));
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Check the cache timestamp.
     *
     * @param oldEntity
     * @param newEntity
     * @return true use the cache
     */
    public static boolean checkCache(BaseViewHolder oldEntity, BaseViewHolder newEntity) {
        return oldEntity != null && newEntity.isSingleton() && oldEntity.getTimestamp() == newEntity.getTimestamp();
    }
}
