<script setup>
import Fundament from "./components/Fundament-Component.vue";
// eslint-disable-next-line no-unused-vars
import Card from "./components/Card.vue";
import CardList from "./components/CardList.vue";
import { onMounted, ref } from "vue";
import axios from "axios";

const items = ref([]);

onMounted(async () => {
  try {
    const { data } = await axios.get("http://localhost:8080/api/v1/albums");
    items.value = data;
    console.log(data);
  } catch (err) {
    console.log(err);
  }
});
</script>

<template>
  <Fundament />
  <div
    class="bg-white w-9/12 m-auto h-auto rounded-xl shadow-xl mt-14 min-h-[80vh]"
  >
    <!-- тут <Card /> -->

    <div>
      <div class="">
        <div class="flex justify-between">
          <div class="flex flex-col"></div>
        </div>
        <div class="mt-6 ml-3 mr-3 mt-6">
          <CardList :items="items" />
        </div>
      </div>
    </div>
  </div>
</template>

<style>
#app {
  max-width: 100%;
  margin: 0;
  box-sizing: border-box;
}
</style>
