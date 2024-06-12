<!-- eslint-disable vue/multi-word-component-names -->
<script setup>
// eslint-disable-next-line no-unused-vars
import { ref, computed } from "vue";

const props = defineProps({
  title: String,
  artist: String,
  imageUrl: String,
  rate: Number,
  id: Number,
});

const formattedRate = computed(() => {
  if (props.rate != 0) {
    return props.rate.toFixed(1);
  }
  return "-";
});

const emit = defineEmits(["open-modal"]);

const rateColorClass = computed(() => {
  if (formattedRate.value >= 7) {
    return "bg-green-500";
  } else if (formattedRate.value >= 4) {
    return "bg-yellow-500";
  } else if (formattedRate.value > 0) {
    return "bg-red-500";
  } else {
    return "bg-gray-500";
  }
});

function openModal() {
  emit("open-modal", {
    title: props.title,
    artist: props.artist,
    imageUrl: props.imageUrl,
    rate: props.rate,
    id: props.id,
  });
}
</script>

<template>
  <div
    class="relative flex flex-col w-full border border-slate-100 rounded-xl p-5 cursor-pointer transition hover:shadow-xl hover:transform ver:-translate-y-2"
    @click="openModal"
  >
    <img
      :src="imageUrl"
      class="object-cover h-22 w-22 w-full rounded-md"
      alt="Album"
    />
    <div class="flex justify-between mt-4">
      <div class="w-3/4">
        <p class="font-bold text-[20px] leading-5">{{ title }}</p>
        <span class="font-light text-[12px]">{{ artist }} </span>
      </div>
      <div
        class="w-6 h-6 rounded bg-green-500 place-content-center flex"
        :class="rateColorClass"
      >
        <span class="font-regular m-auto text-[11px] text-white text-4"
          >{{ formattedRate }}
        </span>
      </div>
    </div>
  </div>
</template>
