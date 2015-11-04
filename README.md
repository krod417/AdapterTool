# AdapterTool
The new universal tool adapter,Reference chenupt@gmail.com。
## 添加依赖
```
dependencies {
    compile 'com.krod.adapter:AdapterTool:1.1.0'
}
```

### ListView+GridView的通用适配器——ModelAdapter
```
  1.实现BaseViewHolder抽象类
      itemViewId －－返回item的布局文件id
      afterViewCreated －－初始化item以及添加点击事件
      bindView－－将数据mode与view绑定
  2.如果使用databinding方式，则实现DataBindViewHolder抽象类
      itemViewId－－返回item的布局文件id
      afterViewCreated －－初始化item以及添加点击事件
      itemModeId 返回数据模型的BR值
  3.源生实现
    ArrayList<BaseViewHolder> data－－通过BaseViewHolder封装的数据模型
    ArrayList<Class> holder;－－对应不同item的BaseViewHolder类
      ViewManager vm = ViewManager.begin();
            for (Class hold : holder) {
                vm.addModel(hold);
            }
      ModelListAdapter adapter ＝ new ModelListAdapter(view.getContext(), vm);
      listView.setAdapter(adapter);
      adapter.setList(data);
  4.通过databinding实现
      在xml中加入data和holder两个属性，在activity中通过ViewDataBinding给这两个属性设值，其中data传入BaseViewHolder列表，holder传入不同item对应的BaseViewHolder类列表。
      <layout xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto">
      <data>
        <import type="com.databinding.kswording.myapplication.listview.ListViewActivity" />
        <import type="java.util.ArrayList" />
        <variable name="holdelist" type="ArrayList" />
        <variable name="datalist" type="ArrayList" />
      </data>
      <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">
        <ListView
            android:id="@+id/listview"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:cacheColorHint="@android:color/transparent"
            android:listSelector="@android:color/transparent"
            android:divider="@null"
            android:dividerHeight="0.0dp"
            app:data="@{datalist}"
            app:holder="@{holdelist}"
            ></ListView>
      </LinearLayout>
      </layout>
```
# License

Copyright 2015 krod

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
