<template></template>
<script setup lang="ts">
import { onMounted } from "vue";
import TypeIt from "typeit";

onMounted(() => {
  new TypeIt(".tagline", {
    strings: [""],
    speed: 200,
  }).go();
});
</script>
