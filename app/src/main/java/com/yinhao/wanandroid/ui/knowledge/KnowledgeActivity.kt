package com.yinhao.wanandroid.ui.knowledge

import android.app.Activity
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.yinhao.commonmodule.base.base.BaseVMActivity
import com.yinhao.wanandroid.databinding.ActivityKnowledgeBinding
import com.yinhao.wanandroid.model.bean.KnowledgeTreeBean
import com.yinhao.wanandroid.ui.fragment.knowledge.KnowledgeFragment
import com.yinhao.wanandroid.widget.ToolbarManager
import org.jetbrains.anko.startActivity

class KnowledgeActivity : BaseVMActivity<KnowledgeViewModel, ActivityKnowledgeBinding>(),
    ToolbarManager {
    override val toolbar: Toolbar by lazy { viewBinding!!.toolbar }
    private val mTabList by lazy { intent.extras?.getSerializable(KEY_DATA) as KnowledgeTreeBean }

    companion object {
        const val KEY_TITLE = "com.yinhao.wanandroid.ui.knowledge.KnowledgeActivity.title"
        const val KEY_DATA = "com.yinhao.wanandroid.ui.knowledge.KnowledgeActivity.data"

        fun actionStart(context: Activity?, title: String, data: KnowledgeTreeBean) {
            context?.startActivity<KnowledgeActivity>(
                KEY_TITLE to title,
                KEY_DATA to data
            )
        }
    }

    override fun initViewModel(): KnowledgeViewModel =
        ViewModelProvider(this).get(KnowledgeViewModel::class.java)

    override fun initViewBinging(inflater: LayoutInflater): ActivityKnowledgeBinding =
        ActivityKnowledgeBinding.inflate(layoutInflater)

    override fun initView() {
        initToolbar()
        initTab()
    }

    override fun initData() {
    }

    private fun initToolbar() {
        toolbarTitle = intent.getStringExtra(KEY_TITLE)
        setSupportActionBar(toolbar)
        enableHomeAsUp(1f) {
            finish()
        }
    }

    private fun initTab() {
        viewBinding?.viewPager?.apply {
            offscreenPageLimit = 2
            adapter = object : FragmentStateAdapter(this@KnowledgeActivity) {
                override fun getItemCount(): Int {
                    return mTabList.children.size
                }

                override fun createFragment(position: Int): Fragment {
                    // 实例化Fragment
                    return KnowledgeFragment.newInstance(mTabList.children[position].id)
                }
            }
        }

        TabLayoutMediator(viewBinding!!.tabLayout, viewBinding.viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = mTabList.children[position].name
            }
        ).attach()
    }

}