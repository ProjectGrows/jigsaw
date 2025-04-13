import { zhNav } from '../navbar'
import { zhSidebar } from '../sidebar'
import dayjs from 'dayjs'
import type { DefaultTheme, LocaleSpecificConfig } from 'vitepress'

export const zhConfig: LocaleSpecificConfig<DefaultTheme.Config> = {
  themeConfig: { // 主题设置
    lastUpdatedText: '上次更新', // 上次更新显示文本
    returnToTopLabel: '返回顶部', // 更改手机端菜单文字显示
    nav: zhNav,
    sidebar: zhSidebar, // 侧边栏
    docFooter: { // 自定义上下页名
      prev: '上一篇', next: '下一篇'
    },
    darkModeSwitchLabel: '深浅模式', // 手机端深浅模式文字修改
    outline: { // 大纲显示 1-6 级标题
      level: [1, 6],
      label: '目录'
    },
    //大纲顶部标题
    outline :{
      label: '当前页大纲'
    },
    editLink: {
      pattern: 'https://github.com/834772509/FirPE-Docs/edit/main/docs/:path',
      text: '在 GitHub 编辑本页'
    },
    search: {
      provider: 'local'
    },

  }
}