package com.yinhao.wanandroid.ui.fragment.home

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yinhao.wanandroid.R
import com.yinhao.wanandroid.model.bean.BannerBean
import com.youth.banner.adapter.BannerAdapter
import com.youth.banner.util.BannerUtils


/**
 * author:  yinhao
 * date:    2020/7/11
 * version: v1.0
 * ### description:
 */
/**
 * 自定义布局，下面是常见的图片样式，更多实现可以看demo，可以自己随意发挥
 */
class ImageAdapter(data: List<BannerBean>?) :
    BannerAdapter<BannerBean, ImageAdapter.BannerViewHolder>(data) {
    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    override fun onCreateHolder(
        parent: ViewGroup,
        viewType: Int
    ): BannerViewHolder {
        val view = BannerUtils.getView(parent, R.layout.layout_banner)

        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        return BannerViewHolder(view)
    }

    override fun onBindView(
        holder: BannerViewHolder,
        data: BannerBean,
        position: Int,
        size: Int
    ) {
        Glide.with(holder.view.context).load(data.imagePath).into(holder.imageView)
        holder.tvDesc.text = data.desc
    }

    inner class BannerViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        val tvDesc: TextView = view.findViewById(R.id.tv_desc)
        val imageView: ImageView = view.findViewById(R.id.image_view)
    }
}