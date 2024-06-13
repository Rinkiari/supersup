<template>
  <div
    v-if="isOpen"
    class="fixed inset-0 bg-gray-800 bg-opacity-75 flex items-center justify-center"
  >
    <div
      class="bg-white rounded-lg p-8 w-9/12 h-full max-w-4xl overflow-auto relative flex"
    >
      <!-- Кнопка закрытия -->
      <button
        @click="closeModal"
        class="absolute top-4 right-4 text-gray-500 hover:text-gray-700"
      >
        &times;
      </button>

      <!-- Левая часть окна: изображение альбома и треклист -->
      <div class="w-3/12 mr-8">
        <img
          :src="album.imageUrl"
          alt="Album Cover"
          class="w-full rounded-lg mb-4"
        />
        <div class="mt-4 p-2 ba bg-gray-100 rounded-lg">
          <h3 class="text-[18px] font-bold flex justify-center m-1">
            Треклист
          </h3>
          <!-- Здесь будут треки -->
          <ol class="list-decimal pl-5 font-light text-[14px]">
            <li v-for="track in album.tracklist" :key="track">
              {{ track }}
            </li>
          </ol>
        </div>
      </div>

      <!-- Правая часть окна: название альбома, рейтинг, отзывы и форма для добавления отзыва -->
      <div class="w-9/12">
        <div class="mb-4">
          <h2 class="font-bold text-[34px]">{{ album.title }}</h2>
          <p class="text-[20px]">{{ album.artist }}</p>

          <div class="mb-4 mt-2">
            <p class="text-gray-700 text-[12px]">Год: {{ album.year }}</p>
            <p class="text-gray-700 text-[12px]">
              Жанры: {{ formatGenres(album.genres) }}
            </p>
          </div>

          <div class="flex items-center mt-4">
            <div
              class="w-12 h-12 rounded-lg place-content-center flex mr-5"
              :class="getRateColorClass(album.rate)"
            >
              <span class="font-medium m-auto text-[20px] text-white text-4"
                >{{ getFormattedRate(album.rate) }}
              </span>
            </div>
            <span
              v-for="star in 10"
              :key="star"
              @click="rateAlbum(star)"
              class="cursor-pointer"
            >
              <svg
                v-if="star <= rating"
                xmlns="http://www.w3.org/2000/svg"
                fill="currentColor"
                class="w-6 h-6 text-yellow-500"
                viewBox="0 0 24 24"
              >
                <path
                  d="M12 .587l3.668 7.568L24 9.432l-6 5.847 1.42 8.292L12 18.765l-7.42 4.805L6 15.279 0 9.432l8.332-1.277z"
                />
              </svg>
              <svg
                v-else
                xmlns="http://www.w3.org/2000/svg"
                fill="currentColor"
                class="w-6 h-6 text-gray-300"
                viewBox="0 0 24 24"
              >
                <path
                  d="M12 .587l3.668 7.568L24 9.432l-6 5.847 1.42 8.292L12 18.765l-7.42 4.805L6 15.279 0 9.432l8.332-1.277z"
                />
              </svg>
            </span>
          </div>
        </div>

        <div>
          <h3 class="text-lg font-semibold mt-5">Рецензии</h3>
          <div
            v-for="review in backendReviews"
            :key="review.id"
            :imageUrl="review.imageUrl"
            class="flex mt-2 bg-amber-50 p-2 rounded-md"
          >
            <img
              :src="review.imageUrl || defaultAvatar"
              alt="Avatar"
              class="w-11 h-11 rounded-full mr-1"
            />
            <div class="ml-1">
              <p class="text-gray-800 font-semibold text-[16px]">
                {{ review.username }}
              </p>
              <p class="text-gray-700 text-[14px]">
                {{ review.text }}
              </p>
            </div>
          </div>
          <input
            v-model="newReviewAuthor"
            placeholder="Ваше имя"
            class="w-full border rounded-lg p-2 mt-2 text-[12px]"
          />
          <textarea
            v-model="newReviewText"
            placeholder="Напишите рецензию"
            class="w-full border rounded-lg p-2 mt-2 text-[12px]"
          ></textarea>
          <div class="flex">
            <button
              @click="addReview"
              class="mt-2 px-4 py-2 bg-orange-400 text-white rounded-lg text-[12px]"
            >
              Отправить
            </button>
            <p
              class="content-center m-4 text-[12px] text-red-500 font-semibold"
              v-if="showMessage"
            >
              Заполните все поля!
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { defineProps, defineEmits, ref, watch, computed } from "vue";
import axios from "axios";
import Card from "@/components/Card.vue";

// Определение входных параметров и событий
const props = defineProps({
  id: Number,
  isOpen: {
    type: Boolean,
    required: true,
  },
  album: {
    type: Object,
    required: true,
  },
  onClose: {
    type: Function,
    required: true,
  },
});

// Локальные переменные
const rating = ref(0); // Рейтинг альбома
const backendReviews = ref([]); // Отзывы с бэкэнда

// Переменная для хранения текста нового отзыва
const newReviewText = ref("");
const newReviewAuthor = ref("");
const showMessage = ref(false);

// Переменная для проверки, был ли рейтинг уже отправлен
const hasRated = ref(false);

//default ava
const defaultAvatar = "/public/images/catavatar.jpeg";

function getFormattedRate(rate) {
  if (rate != 0 && rate != undefined) {
    if (rate != 10) {
      return rate.toFixed(1);
    } else {
      return 10;
    }
  }
  return "-";
}

function getRateColorClass(rate) {
  const formattedRate = getFormattedRate(rate);
  if (formattedRate >= 7) {
    return "bg-green-500";
  } else if (formattedRate >= 4) {
    return "bg-yellow-500";
  } else if (formattedRate > 0) {
    return "bg-red-500";
  } else {
    return "bg-gray-500";
  }
}

function formatGenres(genres) {
  return genres.join(", ");
}

// Функция для закрытия модального окна
function closeModal() {
  props.onClose();
}

// Функция для установки рейтинга альбома
async function rateAlbum(star) {
  if (!hasRated.value) {
    rating.value = star;
    hasRated.value = true;

    // Отправка рейтинга на бэкенд
    try {
      const response = await axios.post(
        `http://localhost:8080/api/v1/rates/save/${props.album.id}/${star}`
      );
      console.log("Rating submitted:", response.data);
    } catch (error) {
      console.error("Error submitting rating:", error);
    }
  } else {
    console.log("You have already rated this album.");
  }
}

// Функция для загрузки отзывов с бэкэнда
async function fetchReviews() {
  try {
    console.log(props.album); // Выведем ID альбома в консоль перед выполнением запроса
    const response = await axios.get(
      `http://localhost:8080/api/v1/reviews/album_id/${props.album.id}`
    );

    console.log(response);
    backendReviews.value = response.data;
  } catch (error) {
    console.error("Error fetching reviews:", error);
  }
}

// Функция для добавления нового отзыва
async function addReview() {
  if (newReviewText.value.trim() && newReviewAuthor.value.trim()) {
    // Создаем новый объект отзыва
    const newReview = {
      username: newReviewAuthor.value.trim(), // Имя пользователя
      text: newReviewText.value.trim(), // Текст отзыва
      //avatar: defaultAvatar,
    };

    try {
      const response = await axios.post(
        `http://localhost:8080/api/v1/reviews/add/${props.album.id}`,
        newReview
      );
      backendReviews.value.push(response.data);
      newReviewText.value = "";
      newReviewAuthor.value = "";
    } catch (error) {
      console.error("Error adding review:", error);
    }

    showMessage.value = false;
  } else {
    showMessage.value = true;
  }
}

watch(
  () => props.album,
  (newAlbum) => {
    rating.value = newAlbum.rating || 0;
    hasRated.value = false; // Сброс состояния оценки при смене альбома
    fetchReviews();
  },
  { immediate: true }
);

watch(
  () => props.isOpen,
  (newIsOpen) => {
    if (newIsOpen) {
      fetchReviews();
    }
  }
);
</script>
