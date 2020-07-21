package com.yinhao.wanandroid.ui.main.system.systemChild

import android.text.Html
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.yinhao.wanandroid.R
import com.yinhao.wanandroid.logic.model.bean.KnowledgeTreeBean

/**
 * author:  yinhao
 * date:    2020/7/21
 * version: v1.0
 * ### description:
 */

class KnowledgeTreeAdapter :
    BaseQuickAdapter<KnowledgeTreeBean, BaseViewHolder>(R.layout.item_knowledge_tree) {
    override fun convert(helper: BaseViewHolder, item: KnowledgeTreeBean) {
        helper.setText(R.id.title_first, item.name)
        item.children.let {
            helper.setText(R.id.title_second,
                it.joinToString("    ", transform = { child ->
                    Html.fromHtml(child.name)
                })
            )

        }
    }
}