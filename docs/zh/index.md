---
# 官方文档相关配置：https://vitepress.dev/reference/default-theme-layout
layout: home
home: true

# 官方文档相关配置：https://vitepress.dev/reference/default-theme-home-page
lang: zh-CN
title: jigsaw
titleTemplate: Hi，终于等到你
editLink: true
lastUpdated: true

# 指定要为当前页面注入的额外头标签。将附加在站点级配置注入的头标签之后
head:
  - - meta
    - name: description
      content: jigsaw
  - - meta
    - name: keywords
      content: jigsaw

hero:
  name: "jigsaw"
  text: "拼图小游戏"
  tagline: "使用 Swing 构建的拼图小游戏 --- jigsaw 🎉"
  image:  # text 和 tagline 区域旁的图片
    src: /logo.svg
    alt: "jigsaw"
  # 按钮相关
  actions:
    - theme: brand
      text: "🏠首页"
      link: "/"      
    - theme: alt
      text: "🚀快速开始"
      link: "/guide/quickstart/"          

# 按钮下方的描述
features:
  - icon: 🧰
    title: "系统安装与维护"
    details: "FirPE 支持系统安装、备份、恢复和维护等功能，能够显著提高系统安装效率。"
  - icon: 💾
    title: "U 盘启动盘制作"
    details: "用户可以通过 FirPE 制作启动 U 盘，插入 U 盘即可启动电脑进行系统操作。"
  - icon: 🗂️
    title: "分区管理"
    details: "采用优化的 U 盘三分区方案，PE 分区自动隐藏，数据区与 PE 区独立，便于数据操作。"
  - icon: 🛠️
    title: "镜像制作与恢复"
    details: "支持生成 ISO 文件，用于刻录或虚拟机调试，同时支持系统镜像备份和恢复。"        
---
