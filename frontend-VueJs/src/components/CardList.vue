<script setup>
import Card from "./Card.vue";
import { ref } from "vue";
import AlbumModal from "./AlbumModal.vue";

defineProps({
  items: Array,
});

const isModalOpen = ref(false);
const selectedAlbum = ref(null);

function openModal(album) {
  console.log("Selected Album:", album);
  selectedAlbum.value = album;
  isModalOpen.value = true;
}

function closeModal() {
  isModalOpen.value = false;
  selectedAlbum.value = null;
}
</script>

<template>
  <div>
    <div class="container mx-auto p-4">
      <div
        v-if="items.length === 0"
        class="justify-center min-w-full min-h-full place-content-center"
      >
        <p class="text-gray-400">Ничего не нашлось...</p>
      </div>
      <div
        class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-5"
      >
        <Card
          v-for="item in items"
          :key="item.id"
          :id="item.id"
          :title="item.title"
          :artist="item.artist"
          :imageUrl="item.imageUrl"
          :rate="item.rate"
          @open-modal="openModal"
        />
      </div>
    </div>
    <AlbumModal
      v-if="selectedAlbum"
      :isOpen="isModalOpen"
      :album="selectedAlbum"
      @close="closeModal"
    />
  </div>
</template>
