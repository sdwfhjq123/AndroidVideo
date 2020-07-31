package com.yinhao.wanandroid.ui.fragment.home

import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.yinhao.wanandroid.R
import com.yinhao.wanandroid.model.bean.ArticleBean

/**
 * author:  yinhao
 * date:    2020/7/14
 * version: v1.0
 * ### description:
 */

class ArticleAdapter : BaseQuickAdapter<ArticleBean, BaseViewHolder>(R.layout.item_article) {

    override fun convert(helper: BaseViewHolder, item: ArticleBean) {
        val authorStr = if (item.author.isNotEmpty()) item.author else item.shareUser
        helper.setText(R.id.tv_article_title, Html.fromHtml(item.title))
            .setText(R.id.tv_article_author, authorStr)
            .setText(R.id.tv_article_date, item.niceDate)
            .setImageResource(
                R.id.iv_like,
                if (item.collect) R.drawable.ic_like else R.drawable.ic_like_not
            )
//            .addOnClickListener(R.id.iv_like)
        val chapterName = when {
            item.superChapterName!!.isNotEmpty() and item.chapterName.isNotEmpty() ->
                "${item.superChapterName} / ${item.chapterName}"
            item.superChapterName.isNotEmpty() -> item.superChapterName
            item.chapterName.isNotEmpty() -> item.chapterName
            else -> ""
        }
        helper.setText(R.id.tv_article_chapterName, chapterName)
        if (item.envelopePic.isNotEmpty()) {
            helper.getView<ImageView>(R.id.iv_article_thumbnail)
                .visibility = View.VISIBLE
            Glide.with(context).load(item.envelopePic)
                .into(helper.getView(R.id.iv_article_thumbnail))
        } else {
            helper.getView<ImageView>(R.id.iv_article_thumbnail)
                .visibility = View.GONE
        }
        val tvFresh = helper.getView<TextView>(R.id.tv_article_fresh)
        if (item.fresh) {
            tvFresh.visibility = View.VISIBLE
        } else {
            tvFresh.visibility = View.GONE
        }
        val tvTop = helper.getView<TextView>(R.id.tv_article_top)
        if (item.top == 1) {
            tvTop.visibility = View.VISIBLE
        } else {
            tvTop.visibility = View.GONE
        }
        val tvArticleTag = helper.getView<TextView>(R.id.tv_article_tag)
        if (item.tags.isNotEmpty()) {
            tvArticleTag.visibility = View.VISIBLE
            tvArticleTag.text = item.tags[0].name
        } else {
            tvArticleTag.visibility = View.GONE
        }
    }
}