---
layout: page
comment: false
---
<script setup>
import {
  VPTeamPage,
  VPTeamPageTitle,
  VPTeamMembers,
  VPTeamPageSection,
} from 'vitepress/theme'

const coreMembers = [
  {
    avatar: '/avatar/compe.png',
    name: 'ComPE',
    title: 'ComPE',
    links: [
      { 
        icon: {
          svg: '<svg t="1742273040728" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2741" width="32" height="32"><path d="M74.873 70.383h416.025q4.424 0 4.424 4.424v416.025q0 4.423-4.424 4.423H74.873q-4.424 0-4.424-4.423V74.807q0-4.424 4.424-4.424z" fill="#0099FF" p-id="2742"></path><path d="M530.536 70.383h421.157q1.858 0 1.858 1.858v421.156q0 1.858-1.858 1.858H530.536q-1.858 0-1.858-1.858V72.241q0-1.858 1.858-1.858z" fill="#00D9FC" p-id="2743"></path><path d="M74.873 528.745h416.025q4.424 0 4.424 4.423v416.025q0 4.424-4.424 4.424H74.873q-4.424 0-4.424-4.424V533.168q0-4.423 4.424-4.423z" fill="#0053FF" p-id="2744"></path><path d="M530.536 528.745h421.157q1.858 0 1.858 1.858v421.156q0 1.858-1.858 1.858H530.536q-1.858 0-1.858-1.858V530.603q0-1.858 1.858-1.858z" fill="#00B1EF" p-id="2745"></path></svg>'
        }, 
        link: 'https://www.win-compe.top' },
    ],
  },
  {
    avatar: 'https://www.github.com/kiaking.png',
    name: 'Kia King Ishii',
    title: 'Developer',
    links: [
      { icon: 'github', link: 'https://github.com/kiaking' },
      { icon: 'twitter', link: 'https://twitter.com/KiaKing85' },
    ],
  },
]
</script>

<VPTeamPage>
  <VPTeamPageTitle>
  </VPTeamPageTitle>
  <VPTeamMembers size="small" :members="coreMembers" />
</VPTeamPage>