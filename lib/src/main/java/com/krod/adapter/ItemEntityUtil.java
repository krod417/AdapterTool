/*
 * Copyright 2015 chenupt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.krod.adapter;


import android.support.v4.app.Fragment;

/**
 * Created by wj19901117@gmail.com on 2015/10/26.
 *
 */
public class ItemEntityUtil {


    /**
     * Check the cache timestamp.
     * @param oldEntity
     * @param newEntity
     * @return true use the cache
     */
    public static boolean checkCache(BaseViewHolder oldEntity, BaseViewHolder newEntity){
        return oldEntity != null && newEntity.isSingleton() && oldEntity.getTimestamp() == newEntity.getTimestamp();
    }

    /**
     * Get default fragment arguments.
     * @param fragment
     * @return
     */
    public static BaseViewHolder getModelData(Fragment fragment){
        return (BaseViewHolder) fragment.getArguments().getSerializable(PagerManager.DATA);
    }

}
