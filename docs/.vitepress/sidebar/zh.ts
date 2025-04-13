import { DefaultTheme } from 'vitepress'

// 中文侧边栏
export const zhSidebar: DefaultTheme.Sidebar = {
  '/': [
    {
      text: '介绍',
      collapsed: false,
      items: [
        { text: '快速开始', link: `/guide/quickstart/` },
        { text: '理念', link: `/guide/philosophy/` },
        { text: '选择 jigsaw 的目的', link: `/guide/aim/` },
      ]
    },
    {
      text: '功能特点',
      collapsed: false,
      items: [
        { text: '核心功能', link: `/feature/core/` },
        { text: '特色亮点', link: `/feature/highlights/` },
      ]
    },
    {
      text: '常见问题',
      collapsed: false,
      items: [
        { text: '常见问题解答', link: `/qa/core/` },
        { text: '故障排除', link: `/qa/exclude/` },
      ]
    },
    {
      text: '支持与捐赠',
      collapsed: false,
      items: [
        { text: '支持方式', link: `/sd/support/` },
        { text: '捐赠渠道', link: `/sd/donation/` },
      ]
    },
    {
      text: '其它',
      collapsed: false,
      items: [
        { text: '发展历程', link: `/other/history/` },
        { text: '友情链接', link: `/other/links/` },
      ]
    },
  ],
}