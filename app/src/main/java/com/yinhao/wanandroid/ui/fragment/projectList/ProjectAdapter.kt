package com.yinhao.wanandroid.ui.fragment.projectList

import android.text.Html
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.yinhao.wanandroid.R
import com.yinhao.wanandroid.logic.model.bean.ArticleBean

/**
 * Created by chenxz on 2018/5/20.
 */
class ProjectAdapter : BaseQuickAdapter<ArticleBean, BaseViewHolder>(R.layout.item_project_list) {

    override fun convert(helper: BaseViewHolder, item: ArticleBean) {
        val authorStr = if (item.author.isNotEmpty()) item.author else item.shareUser
        helper.setText(R.id.item_project_list_title_tv, Html.fromHtml(item.title))
            .setText(R.id.item_project_list_content_tv, Html.fromHtml(item.desc))
            .setText(R.id.item_project_list_time_tv, item.niceDate)
            .setText(R.id.item_project_list_author_tv, authorStr)
            .setImageResource(
                R.id.item_project_list_like_iv,
                if (item.collect) R.drawable.ic_like else R.drawable.ic_like_not
            )
//            .addOnClickListener(R.id.item_project_list_like_iv)
        Glide.with(context).load(item.envelopePic)
            .into(helper.getView(R.id.iv_article_thumbnail))
    }
}