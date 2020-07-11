package com.yinhao.wanandroid.ui.home.home

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yinhao.wanandroid.logic.model.bean.BannerBean
import com.youth.banner.adapter.BannerAdapter

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
        val imageView = ImageView(parent.context)
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return BannerViewHolder(imageView)
    }

    override fun onBindView(
        holder: BannerViewHolder,
        data: BannerBean,
        position: Int,
        size: Int
    ) {
        Glide.with(holder.imageView).load(data.imagePath).into(holder.imageView)
    }

    inner class BannerViewHolder(var imageView: ImageView) :
        RecyclerView.ViewHolder(imageView)
}