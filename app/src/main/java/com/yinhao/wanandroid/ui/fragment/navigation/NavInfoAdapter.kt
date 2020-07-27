package com.yinhao.wanandroid.ui.fragment.navigation

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.donkingliang.labels.LabelsView
import com.yinhao.wanandroid.R
import com.yinhao.wanandroid.logic.model.bean.ArticleBean
import com.yinhao.wanandroid.logic.model.bean.NavigationBean
import org.jetbrains.anko.toast
import team.fcma.xframe.others.XFrameCommonMethods


/**
 * author:  yinhao
 * date:    2020/7/22
 * version: v1.0
 * ### description:
 */
class NavInfoAdapter : BaseQuickAdapter<NavigationBean, BaseViewHolder>(R.layout.item_nav_info) {
    override fun convert(helper: BaseViewHolder, item: NavigationBean) {
        helper.setText(R.id.tv_nav_name, item.name)
        helper.getView<LabelsView>(R.id.labels_view)
            .setLabels(item.articles) { label, position, data ->
                // label就是标签项，在这里可以对标签项单独设置一些属性，比如文本样式等。
                label.setTextColor(XFrameCommonMethods.randomColor())
                //根据data和position返回label需要显示的数据。
                return@setLabels data.title
            }

        //标签的点击监听
        helper.getView<LabelsView>(R.id.labels_view)
            .setOnLabelClickListener { label, data, position ->
                //label是被点击的标签，data是标签所对应的数据，position是标签的位置。
                val bean = data as ArticleBean
                context.toast(bean.title)
            }
    }

}