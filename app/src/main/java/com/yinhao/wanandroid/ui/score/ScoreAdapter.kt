package com.yinhao.wanandroid.ui.score

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.yinhao.wanandroid.R
import com.yinhao.wanandroid.model.bean.UserScoreBean

/**
 * @author chenxz
 * @date 2019/9/5
 * @desc
 */
class ScoreAdapter : BaseQuickAdapter<UserScoreBean, BaseViewHolder>(R.layout.item_score_list) {

    override fun convert(helper: BaseViewHolder, item: UserScoreBean) {
        helper.setText(R.id.tv_reason, item.reason)
            .setText(R.id.tv_desc, item.desc)
            .setText(R.id.tv_score, "+${item.coinCount}")
    }
}