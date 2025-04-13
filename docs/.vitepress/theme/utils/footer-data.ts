import type { FooterData } from '@theojs/lumen'

export const Footer_Data: FooterData = {
  beian: { icp: '赣ICP备18007921号-1', showIcon: true },
  author: { name: '杉', link: 'https://github.com/834772509' },
  group: [
    {
      title: '相关博客',
      icon: 'bx:link', // `iconify`图标
      links: [
        { name: '作者博客', link: 'http://blog.firpe.cn', icon: 'noto-v1:books' },
      ]
    },
    {
      title: '合作伙伴',
      icon: 'bx:link',
      links: [
        { name: '火数云', icon: '', link: 'https://www.hsy.com' },
      ]
    },
    {
      title: '相关链接',
      icon: 'bx:link',
      links: [
        { name: 'VitePress', icon: 'pajamas:markdown-mark', link: 'https://vitepress.dev' },
        { name: 'Vue', icon: 'logos:vue', link: 'https://cn.vuejs.org' },
        { name: 'Vite', icon: 'vscode-icons:file-type-vite', link: 'https://cn.vite.dev' },
      ]
    }
  ]
}